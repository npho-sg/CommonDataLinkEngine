package com.example.integration.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.example.integration.exception.ReadException;

public class TextWriter implements Writer {

	public void write(List<String> messageList, String filePath) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

			for (String message : messageList) {

				bw.write(message);
				bw.newLine();
			}

		} catch (IOException e) {

			throw new ReadException("出力先ファイルがみつかりません", e);

		}

	}

}
