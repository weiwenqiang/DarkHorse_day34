package com.example.day34.observer;

import com.example.day34.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

public class TestObserver extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b6_insertcontacts);
		
		//注册一个内容观察者，监听短信数据库内容的改变
		ContentResolver cr = getContentResolver();
		//uri:监听那个uri上的内容提供者的通知
		//notifyForDescendents:如果是true只要是指定uri开头的URI数据改变，都能收到通知；false要精确匹配
		cr.registerContentObserver(Uri.parse("content://sms"), true, new MyObserver(new Handler()));
	}

	class MyObserver extends ContentObserver{

		public MyObserver(Handler handler) {
			super(handler);
		}
		//	收到数据改变的通知，此方法调用
		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Log.d("内容观察者", "短信数据库改变了");
		}
		
	}
}
