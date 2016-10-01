package com.example.day34.listenerobserver;

import com.example.day34.R;

import android.app.Activity;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

public class TestListenerObserver extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b6_insertcontacts);
		
		getContentResolver().registerContentObserver(Uri.parse("content://text.person.provider"), true, new ContentObserver(new Handler()) {

					@Override
					public void onChange(boolean selfChange) {
						super.onChange(selfChange);
						Log.d("内容观察者", "自定义内容提供者被内容观察者监听了");
					}
		});
	}

}
