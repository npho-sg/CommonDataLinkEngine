package com.example.integration.converter;

import com.example.integration.model.Devconfig;

public class MessageBuilder implements TextBuilder{

	public String build(Devconfig config) {
		
		return config.deviceId() + "|" + config.ip()+ "|" + config.port();
	}
}
