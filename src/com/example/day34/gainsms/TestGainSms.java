package com.example.day34.gainsms;


import com.example.day34.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class TestGainSms extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b2_gainsms);
		findViewById(R.id.b2_gainsms).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b2_gainsms:
			//访问内容提供者获取短信
			ContentResolver cr = getContentResolver();
			//短信内容提供者的主机名
			Cursor cursor = cr.query(Uri.parse("content://sms"), new String[]{"body","address","date","type"}, null, null, null);
			while(cursor.moveToNext()){
				String body = cursor.getString(0);
				String address = cursor.getString(1);
				long date = cursor.getLong(2);
				String type= cursor.getString(3);
				Log.d("TestBackupsSms", body+";"+address+";"+date+";"+type);
			}
			break;
		default:
			break;
		}
	}
}
