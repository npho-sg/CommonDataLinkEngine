package com.example.integration;

import java.util.List;

import com.example.integration.Reader.Reader;
import com.example.integration.exception.ReadException;
import com.example.integration.exception.ValidationException;
import com.example.integration.model.Devconfig;
import com.example.integration.validator.DevconfigValidator;

public class Main {

	public static void main(String[] args) {

		try {

			Reader r = new Reader();
			List<Devconfig> list = r.read(args[0]);
			//list.forEach(System.out::println);

			DevconfigValidator validator = new DevconfigValidator();

			for (Devconfig config : list) {

				try {
					validator.validate(config);
					System.out.println("ok : " + config);
				} catch (ValidationException e) {
					System.out.println("NG : " + config + e.getMessage());
				}

			}
		} catch (ReadException e) {
			System.out.println("ファイル読込失敗" + e.getMessage());

		}

	}

}
