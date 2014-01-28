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
			//�T�[�o�[�\�P�b�g����
			ServerSocket ss = new ServerSocket(5000);
			
			//client����̗v���҂�
			Socket s = ss.accept();
			
			//�f�[�^��M
			InputStream reader = s.getInputStream();
			while(true){
				if(reader.available() > 4) break;
			}
			
			//�摜�f�[�^�̒�����ǂݍ���
			int len3 = reader.read();
			int len2 = reader.read();
			int len1 = reader.read();
			int len0 = reader.read();
			int len = len3<<24 | len2<<16 | len1<<8 | len0;
			
			while(true){
				if(reader.available() >= len) break;
			}
			
			byte[] bytes = new byte[len];
			//�摜�f�[�^��ǂݍ���
			reader.read(bytes);

			//byte[]��Bitmap�ɕϊ�
			if(bytes != null){
				bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			}
			
			s.close();		
			ss.close();

		}catch(IOException e) {
			e.printStackTrace();
		}
		
		final Bitmap bmp1 = bmp;
		//�摜��ImageView�ɕ\��
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
	//�R���X�g���N�^
	public Recv(Activity a, Handler h){
		handler = h;
		iv = (ImageView)a.findViewById(R.id.imageView1);
	}
}