package com.example.day34.insertsms;

import com.example.day34.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class TestInsertSms extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b4_insertsms);
		findViewById(R.id.b4_insertsms).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b4_insertsms:
			Thread t = new Thread(){

				@Override
				public void run() {
					try {
						sleep(7500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ContentResolver cr = getContentResolver();
					ContentValues values = new ContentValues();
					values.put("address", 95555);
					values.put("type", 1);
					values.put("date", System.currentTimeMillis());
					values.put("body", "您为好为xxxx的信用卡收到1,000,000RMB转账，请注意查收");
					cr.insert(Uri.parse("content://sms"), values);
				}
			};
			t.start();
			break;
		default:
			break;
		}
	}

}
