package com.example.integration.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.example.integration.exception.ReadException;
import com.example.integration.model.Devconfig;

public class Reader {
	
	public List<Devconfig> read(String filePath){
		
		try(BufferedReader br = new BufferedReader(new FileReader(filePath));){
			
			List<Devconfig> list = new ArrayList<>();
			
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(br);
			
			for (CSVRecord r : records) {
				
				String deviceId = r.get(0);
				String ip = r.get(1);
				String port = r.get(2);
				
				Devconfig config = new Devconfig(deviceId, ip, port);
				
				list.add(config);
			}
			
			return list;
		
		} catch(IOException e) {
			
			throw new ReadException("読み込み失敗", e);
		}
		
	}

}
