package com.example.send;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Send extends Thread{
	public void run() {
		try {
			//IPアドレスとポート番号指定
			String server = "192.168.1.3";
    		int servPort = 5000;
    		
    		//ソケット作成
    		Socket socket = new Socket(server, servPort);
    		System.out.println("サーバとの接続を確立。");
    		
    		//データ送信
    		OutputStream out = socket.getOutputStream();
    		
    		//画像データの長さを送信
    		out.write((data.length>>24)&0xFF);
    		out.write((data.length>>16)&0xFF);
    		out.write((data.length>>8)&0xFF);
    		out.write((data.length>>0)&0xFF);
    		
    		//画像データを送信
    		out.write(data);
    		System.out.println("送信:" + new String(data));
    		
    		socket.close();
    		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	byte[] data = new byte[1024];
	//コンストラクタ
	public Send(byte[] bytes){
		//画像データを代入
		data = bytes;
	}
}