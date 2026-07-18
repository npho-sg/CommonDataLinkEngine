package com.example.integration.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextWriter {
	
	public void write(List<String> messageList, String filePath) {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
			
			for(String message : messageList) {
				
				bw.write(message);
				bw.newLine();
	    	}
			
		}catch(IOException e){
			
			throw new RuntimeException("ファイル読み込み失敗", e);
			
		}
		
	}

}
