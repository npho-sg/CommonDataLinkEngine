package com.example.integration;

import java.util.ArrayList;
import java.util.List;

import com.example.integration.converter.MessageBuilder;
import com.example.integration.converter.TextBuilder;
import com.example.integration.exception.ReadException;
import com.example.integration.exception.ValidationException;
import com.example.integration.model.Devconfig;
import com.example.integration.reader.filereader.InputReader;
import com.example.integration.reader.filereader.Reader;
import com.example.integration.reader.textreader.TempReader;
import com.example.integration.reader.textreader.TextReader;
import com.example.integration.tcp.client.TcpClient;
import com.example.integration.validator.DevconfigValidator;
import com.example.integration.validator.Validator;
import com.example.integration.writer.TextWriter;
import com.example.integration.writer.Writer;

public class Main {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("引数が不正です。読み込みファイル、ポート番号の順で入力してください");
			System.exit(1);
		}

		try {

			Reader r = new InputReader();
			List<Devconfig> list = r.read(args[0]);

			Validator validator = new DevconfigValidator();

			List<String> messageList = new ArrayList<>();

			for (Devconfig config : list) {

				try {
					validator.validate(config);

					TextBuilder builder = new MessageBuilder();
					String message = builder.build(config);

					messageList.add(message);

				} catch (ValidationException e) {
					System.out.println("NG : " + config + e.getMessage());
				}

			}

			Writer writer = new TextWriter();
			writer.write(messageList, "output/send.dat");

			TextReader reader = new TempReader();
			byte[] data = reader.read("output/send.dat");

			int port = Integer.parseInt(args[1]);
			TcpClient client = new TcpClient();
			client.send("localhost", port, data);

		} catch (ReadException e) {
			System.out.println("ファイル読込失敗：" + e.getMessage());

		}

	}

}