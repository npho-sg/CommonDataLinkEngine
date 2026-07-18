package com.example.integration.tcp.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
	
	public void connect(String host, int port, String message) {
		
		try (Socket socket = new Socket(host, port)){
			
			OutputStream os = socket.getOutputStream();
			os.write(message.getBytes());
			os.flush();
			
			System.out.println("送信成功");
			
			InputStream is = socket.getInputStream();
			byte[] buffer = new byte[1024];
			int len = is.read(buffer);
			
			System.out.println("応答：" + new String(buffer, 0, len));
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void send(String host, int port, byte[] data) {
		
		try (Socket socket = new Socket(host, port)) {
			
			OutputStream os = socket.getOutputStream();
			os.write(data);
			os.flush();
			
			System.out.println("送信成功");
			
			InputStream is = socket.getInputStream();
			byte[] buffer = new byte[1024];
			int len = is.read(buffer);
			
			System.out.println("応答" + new String(buffer, 0, len));
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
