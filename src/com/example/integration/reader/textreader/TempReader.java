package com.example.integration.reader.textreader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TempReader implements TextReader {

	@Override
	public byte[] read(String filePath) {
		
		try {
			
			String content = Files.readString(Path.of(filePath));
			return content.getBytes(StandardCharsets.UTF_8);
			
		}catch(IOException e) {
		
		throw new RuntimeException("ファイル読み込み失敗", e);
		
		}
		
	}

}
