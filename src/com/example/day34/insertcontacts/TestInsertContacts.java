package com.example.day34.insertcontacts;

import com.example.day34.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class TestInsertContacts extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b6_insertcontacts);
		findViewById(R.id.b6_insertcontacts).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b6_insertcontacts:
			ContentResolver cr = getContentResolver();
			ContentValues values = new ContentValues();
			//�Ȳ�ѯraw_contacts��,��ȡ������ϵ�˵�������Ȼ������+1������Ҫ�������ϵ�˵�id
			Cursor cursorContactId = cr.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"_id"}, null, null, null);
			int contact_id =1;
			if(cursorContactId.moveToLast()){
				//�õ�������+1
				int _id = cursorContactId.getInt(0);
				//����+1������Ҫ�������ϵ��id
				contact_id =++_id;
			}
			values.put("contact_id", contact_id);
			//����ϵ�˵�id����raw_contacts���ݿ�
			cr.insert(Uri.parse("content://com.android.contacts/raw_contacts"), values);
			
			values.clear();
			
			values.put("data1", "����");
			values.put("mimetype", "vnd.android.cursor.item/name");
			values.put("raw_contact_id", contact_id);
			cr.insert(Uri.parse("content://com.android.contacts/data"), values);
			values.clear();
			
			values.put("data1", "13436434");
			values.put("mimetype", "vnd.android.cursor.item/phone_v2");
			values.put("raw_contact_id", contact_id);
			cr.insert(Uri.parse("content://com.android.contacts/data"), values);
			
			break;

		default:
			break;
		}
	}

}
