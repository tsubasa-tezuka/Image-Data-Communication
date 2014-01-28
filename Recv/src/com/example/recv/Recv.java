package com.example.recv;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

public class Recv extends Thread {
	public void run() {
		Bitmap bmp = null;
		try{
			//サーバーソケット生成
			ServerSocket ss = new ServerSocket(5000);
			
			//clientからの要求待ち
			Socket s = ss.accept();
			
			//データ受信
			InputStream reader = s.getInputStream();
			while(true){
				if(reader.available() > 4) break;
			}
			
			//画像データの長さを読み込み
			int len3 = reader.read();
			int len2 = reader.read();
			int len1 = reader.read();
			int len0 = reader.read();
			int len = len3<<24 | len2<<16 | len1<<8 | len0;
			
			while(true){
				if(reader.available() >= len) break;
			}
			
			byte[] bytes = new byte[len];
			//画像データを読み込み
			reader.read(bytes);

			//byte[]をBitmapに変換
			if(bytes != null){
				bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			}
			
			s.close();		
			ss.close();

		}catch(IOException e) {
			e.printStackTrace();
		}
		
		final Bitmap bmp1 = bmp;
		//画像をImageViewに表示
		handler.post(new Runnable() {
			@Override
			public void run() {
				iv.setImageBitmap(bmp1);
			}
		});
	}

	Activity mainActivity;
	Handler handler;
	ImageView iv;
	//コンストラクタ
	public Recv(Activity a, Handler h){
		handler = h;
		iv = (ImageView)a.findViewById(R.id.imageView1);
	}
}