package com.example.integration.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {

	public static void main(String[] args) throws Exception {

		/*
		TcpServer server = new TcpServer();
		
		Thread serverThread = new Thread(() -> {
		    try {
		        server.start(5000);
		    } catch (Exception e) {
		        
		        System.out.println("テストクラス実行失敗");           }
		});
		
		serverThread.start();
		
		// accept()待機状態になるまで待つ
		Thread.sleep(5000);
		
		// ServerSocketを取得してクローズ
		server.close();
		
		System.out.println("close実行");
		*/

		try (ServerSocket serverSocket = new ServerSocket(5000)) {

			System.out.println("接続待ち port=");

			while (true) {

				try (Socket socket = serverSocket.accept();) {

					System.out.println("接続されました");

					InputStream is = socket.getInputStream();
					byte[] buffer = new byte[1024];
					int len = is.read(buffer);
					System.out.println(new String(buffer, 0, len));
					OutputStream os = socket.getOutputStream();
					os.write("ACK".getBytes());
					os.flush();

				} catch (IOException e) {

					e.printStackTrace();

				}
			}

		} catch (IOException e) {

			System.out.println("2重");
			e.printStackTrace();

		}

	}

}