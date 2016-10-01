package com.example.day34.customprovider;

import com.example.day34.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class TestCustomProvider extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b1_customprovider);
		findViewById(R.id.b1_insert).setOnClickListener(this);
		findViewById(R.id.b1_update).setOnClickListener(this);
		findViewById(R.id.b1_delete).setOnClickListener(this);
		findViewById(R.id.b1_select).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b1_insert:
		{
			//拿到ContentResolver
			ContentResolver cr = getContentResolver();
			
			ContentValues values = new ContentValues();
			values.put("name", "赵帅哥");
			//uri:内容提供者的主机名
			cr.insert(Uri.parse("content://text.person.provider/teacher"), values);
//			values.put("name", "中志");
//			values.put("money", "15000");
//			//uri:内容提供者的主机名
//			cr.insert(Uri.parse("content://text.person.provider"), values);
//			values.put("name", "大志");
//			values.put("money", "22000");
//			//uri:内容提供者的主机名
//			cr.insert(Uri.parse("content://text.person.provider"), values);
			break;
		}
		case R.id.b1_update:
		{
			//拿到ContentResolver
			ContentResolver cr = getContentResolver();
			ContentValues values = new ContentValues();
			values.put("name", "sb志");
			values.put("money", "3000");
			int i = cr.update(Uri.parse("content://text.person.provider"), values, "name = ?", new String[]{"大志"});
			Log.d("update", i+""); 
			break;
		}
		case R.id.b1_delete:
		{
			//拿到ContentResolver
			ContentResolver cr = getContentResolver();
			int i = cr.delete(Uri.parse("content://text.person.provider"), "name = ?", new String[]{"小志"});
			Log.d("delete", i+""); 
			break;
		}
		case R.id.b1_select:
		{
			//拿到ContentResolver
			ContentResolver cr = getContentResolver();
			Cursor cursor = cr.query(Uri.parse("content://text.person.provider/person/4"), null, null, null, null);
			while(cursor.moveToNext()){
				String name = cursor.getString(1);
				String money = cursor.getString(2);
				Log.d("查询", name+";"+money);
			}
			break;
		}
		default:
			break;
		}
	}

}
