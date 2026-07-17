package com.example.integration.tcp.client;

public class ClientMain {
	
	public static void main(String[] args) {
		
		TcpClient client = new TcpClient();
		
		String message = args[0];
		
		client.connect("localhost", 5000, message);	
		
	}

}
