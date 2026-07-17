package com.example.integration.tcp.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	public void start(int port) {

		try (ServerSocket serverSocket = new ServerSocket(port)) {

			System.out.println("接続待ち port=" + port);

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

			e.printStackTrace();

		}
	}
}