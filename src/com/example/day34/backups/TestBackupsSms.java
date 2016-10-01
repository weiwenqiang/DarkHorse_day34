package com.example.day34.backups;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import com.example.day34.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class TestBackupsSms extends Activity implements OnClickListener {
	List<SmsMessage> smsList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b3_backupssms);
		findViewById(R.id.b3_gainsms).setOnClickListener(this);
		findViewById(R.id.b3_backups).setOnClickListener(this);
		smsList = new ArrayList<SmsMessage>();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b3_gainsms:
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
				SmsMessage sms = new SmsMessage(body,type,address,date);
				smsList.add(sms);
			}
			break;
		case R.id.b3_backups:
			XmlSerializer xs = Xml.newSerializer();
			File file = new File("sdcard/sms.xml");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				xs.setOutput(fos, "utf-8");
				
				xs.startDocument("utf-8", true);
				xs.startTag(null, "message");
				
				for (SmsMessage sms : smsList) {
					xs.startTag(null, "sms");
					
					xs.startTag(null, "body");
					xs.text(sms.getBody());
					xs.endTag(null, "body");
					
					xs.startTag(null, "type");
					xs.text(sms.getType());
					xs.endTag(null, "type");
					
					xs.startTag(null, "address");
					xs.text(sms.getAddress());
					xs.endTag(null, "address");
					
					xs.startTag(null, "date");
					xs.text(sms.getDate()+"");
					xs.endTag(null, "date");
					
					xs.endTag(null, "sms");
				}
				
				xs.endTag(null, "message");
				xs.endDocument();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

}
