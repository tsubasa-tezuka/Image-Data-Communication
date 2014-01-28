package com.example.send;

import java.io.ByteArrayOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{

	ImageView iv;
	byte[] data;
	Bitmap img;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        iv = (ImageView)findViewById(R.id.imageView1);
        
        findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		findViewById(R.id.button3).setOnClickListener(this);
		findViewById(R.id.button4).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onClick(View v) {
    	Button btn = (Button) v;
    	if(btn.getId() == R.id.button1){
    		//画像データ送信
    		 new Send(data).start();
    	}
    	else{
    		//画像読み込み
    		Resources res = this.getBaseContext().getResources();
    		if (btn.getId() == R.id.button2){
    			img = BitmapFactory.decodeResource(res, R.drawable.gazou);
    		}
    		else if (btn.getId() == R.id.button3){
    			img = BitmapFactory.decodeResource(res, R.drawable.huji);
    		}
    		else if (btn.getId() == R.id.button4){
    			img = BitmapFactory.decodeResource(res, R.drawable.neko);
    		}
    		
    		//画像表示
    		iv.setImageBitmap(img);
    		
    		//Bitmapをbyteに変換
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		img.compress(CompressFormat.PNG, 100, baos);
    		data = baos.toByteArray();
    	}
    }
}