package com.example.integration;

import java.util.ArrayList;
import java.util.List;

import com.example.integration.converter.MessageBuilder;
import com.example.integration.converter.TextBuilder;
import com.example.integration.exception.ReadException;
import com.example.integration.exception.ValidationException;
import com.example.integration.model.Devconfig;
import com.example.integration.reader.filereader.Reader;
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

					//String[] hoge = {message};
					//ClientMain.main(hoge);

				} catch (ValidationException e) {
					System.out.println("NG : " + config + e.getMessage());
				}

			}
			
			TextWriter writer = new TextWriter();
			writer.write(messageList, "output/send.dat");

		} catch (ReadException e) {
			System.out.println("ファイル読込失敗" + e.getMessage());

		}

	}

}