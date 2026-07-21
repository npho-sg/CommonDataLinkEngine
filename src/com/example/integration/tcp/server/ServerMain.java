package com.example.integration.tcp.server;

public class ServerMain {

	public static void main(String[] args) {

		TcpServer server = new TcpServer();
		server.start(5000);//5000で実行

	}

}
