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
		
		//ע��һ�����ݹ۲��ߣ������������ݿ����ݵĸı�
		ContentResolver cr = getContentResolver();
		//uri:�����Ǹ�uri�ϵ������ṩ�ߵ�֪ͨ
		//notifyForDescendents:�����trueֻҪ��ָ��uri��ͷ��URI���ݸı䣬�����յ�֪ͨ��falseҪ��ȷƥ��
		cr.registerContentObserver(Uri.parse("content://sms"), true, new MyObserver(new Handler()));
	}

	class MyObserver extends ContentObserver{

		public MyObserver(Handler handler) {
			super(handler);
		}
		//	�յ����ݸı��֪ͨ���˷�������
		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Log.d("���ݹ۲���", "�������ݿ�ı���");
		}
		
	}
}
