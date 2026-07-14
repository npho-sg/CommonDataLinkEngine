package com.example.integration.validator;

import com.example.integration.exception.ValidationException;
import com.example.integration.model.Devconfig;

public class DevconfigValidator {

	public void validate(Devconfig config){
		
		if (config.deviceId().isBlank()){
			throw new ValidationException("deviceIdが空です");
		}
		if (config.ip().isBlank()){
			throw new ValidationException("Idが空です");
		}
		if (config.port().isBlank()){
			throw new ValidationException("portが空です");
		}
		
		int port;
		
		try {
			port = Integer.parseInt(config.port().trim());
		} catch(NumberFormatException e){
			throw new ValidationException("ポート番号が数値ではありません", e);
		}
		
		if (port < 1 || port > 65535 ) {
			throw new ValidationException("ポート番号が範囲外です");
		}
	}
	
}
