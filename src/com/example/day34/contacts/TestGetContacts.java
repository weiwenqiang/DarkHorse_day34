package com.example.day34.contacts;

import com.example.day34.MainActivity;
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

public class TestGetContacts extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b5_contacts);
		findViewById(R.id.b5_contacts).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b5_contacts:
			//ͨ�������ṩ��
			ContentResolver cr = getContentResolver();
			Cursor cursorContactId = cr.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"contact_id"}, null, null, null);
			while(cursorContactId.moveToNext()){
				String contactId = cursorContactId.getString(0);
				Cursor cursorData = cr.query(Uri.parse("content://com.android.contacts/data"), new String[]{"data1", "mimetype"},
						"raw_contact_id = ?", new String[]{contactId}, null);
				/*Cursor cursorData = cr.query(Uri.parse("content://com.android.contacts/data"), null,
						"raw_contact_id = ?", new String[]{contactId}, null);
				//��ȡ�����ֶε�����
				String[] names = cursorData.getColumnNames();
				Log.d("�ָ���", "----------------------------");
				for (String string : names) {
					Log.d("�����ֶ���", string);
				}
				Log.d("�ָ���", "----------------------------");*/
				
				Contact con = new Contact();
				while(cursorData.moveToNext()){
					String data1 = cursorData.getString(0);
					String mimetype = cursorData.getString(1);
					if("vnd.android.cursor.item/email_v2".equals(mimetype)){
						con.setEmail(data1);
					}else if("vnd.android.cursor.item/phone_v2".equals(mimetype)){
						con.setPhone(data1);
					}else if("vnd.android.cursor.item/name".equals(mimetype)){
						con.setName(data1);
					}
				}
				Log.d("��ϵ��", con.toString());
			}
			break;

		default:
			break;
		}
	}
}