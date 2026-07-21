package com.example.integration;

import java.util.ArrayList;
import java.util.List;

import com.example.integration.converter.MessageBuilder;
import com.example.integration.converter.TextBuilder;
import com.example.integration.exception.ReadException;
import com.example.integration.exception.ValidationException;
import com.example.integration.model.Devconfig;
import com.example.integration.reader.filereader.Reader;
import com.example.integration.reader.textreader.TempReader;
import com.example.integration.reader.textreader.TextReader;
import com.example.integration.tcp.client.TcpClient;
import com.example.integration.validator.DevconfigValidator;
import com.example.integration.writer.TextWriter;

public class Main {

	public static void main(String[] args) {

		try {

			Reader r = new Reader();
			List<Devconfig> list = r.read(args[0]);

			DevconfigValidator validator = new DevconfigValidator();

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
			
			TextWriter writer = new TextWriter();
			writer.write(messageList, "output/send.dat");
			
			TextReader reader = new TempReader();
			byte[] data = reader.read("output/send.dat");
			
			TcpClient client = new TcpClient();
			client.send("localhost", 500, data);

		} catch (ReadException e) {
			System.out.println("ファイル読込失敗：" + e.getMessage());

		}

	}

}