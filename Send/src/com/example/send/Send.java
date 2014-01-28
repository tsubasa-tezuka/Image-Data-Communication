package com.example.send;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Send extends Thread{
	public void run() {
		try {
			//IP�A�h���X�ƃ|�[�g�ԍ��w��
			String server = "192.168.1.3";
    		int servPort = 5000;
    		
    		//�\�P�b�g�쐬
    		Socket socket = new Socket(server, servPort);
    		System.out.println("�T�[�o�Ƃ̐ڑ����m���B");
    		
    		//�f�[�^���M
    		OutputStream out = socket.getOutputStream();
    		
    		//�摜�f�[�^�̒����𑗐M
    		out.write((data.length>>24)&0xFF);
    		out.write((data.length>>16)&0xFF);
    		out.write((data.length>>8)&0xFF);
    		out.write((data.length>>0)&0xFF);
    		
    		//�摜�f�[�^�𑗐M
    		out.write(data);
    		System.out.println("���M:" + new String(data));
    		
    		socket.close();
    		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	byte[] data = new byte[1024];
	//�R���X�g���N�^
	public Send(byte[] bytes){
		//�摜�f�[�^����
		data = bytes;
	}
}