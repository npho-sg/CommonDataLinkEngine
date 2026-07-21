package com.example.integration.layout.resolver;

import java.util.List;

import com.example.integration.exception.ReadException;
import com.example.integration.layout.filetype.FileType;

public class DevconfigLayoutResolver implements LayoutResolver{

	@Override
	public FileType resolve(List<String> headers) {
		
		if(headers.size() == 3
				&& "DEVICE_ID".equals(headers.get(0))
				&& "IP".equals(headers.get(1))
				&& "PORT".equals(headers.get(2))){
			
			return FileType.DEVICE_CONFIG_V1;
			
		}else if(headers.size() == 3
				&& !headers.get(0).isBlank()
				&& !headers.get(1).isBlank()
				&& !headers.get(2).isBlank()
				) {
			
			return FileType.DEVICE_CONFIG_NO_HEADER_V1;
			
		}
		throw new ReadException("ヘッダ項目数不正");	
	}

}
