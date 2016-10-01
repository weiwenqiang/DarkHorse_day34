package com.example.day34.customprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {

	private MyOpenHelper oh;
	SQLiteDatabase db;
	
	//����URIƥ����
	static UriMatcher um = new UriMatcher(UriMatcher.NO_MATCH);
	//��������û������uri��ƥ��������õ�URI�У�����ƥ��
	static{
		um.addURI("text.person.provider", "person", 1);
		um.addURI("text.person.provider", "teacher", 2);
		um.addURI("text.person.provider", "person/#", 2);//#ƥ����������
	}
	
	
	@Override
	public boolean onCreate() {
		oh = new MyOpenHelper(getContext());
		db = oh.getWritableDatabase();
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		if(um.match(uri)==1){
			cursor =db.query("person", projection, selection, selectionArgs, null, null, sortOrder, null);
		}else if(um.match(uri)==2){
			cursor =db.query("teacher", projection, selection, selectionArgs, null, null, sortOrder, null);
		}else if(um.match(uri)==3){
			//��uriĩβЯ��������ȡ����
			long id = ContentUris.parseId(uri);
			cursor =db.query("person", projection, "_id = ?", new String[]{id+""}, null, null, sortOrder, null);
		}else{
			throw new IllegalArgumentException("uri��������Ŷ");
		}
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		if(um.match(uri)==1){
			return "vnd.android.cursor.dir/person";
		}else if(um.match(uri)==3){
			return "vnd.android.cursor.item/person";
		}
		return null;
	}
	//�˷���������Ӧ�õ��ã�������person���ݿ����������
	//values��������Ӧ�ô��룬���ڷ�װҪ���������
	//uri:�����ṩ�ߵ���������Ҳ���ǵ�ַ
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		//ʹ��uriƥ����ƥ�䴫���uri
		if(um.match(uri)==1){
			db.insert("person", null, values);
			
			//�������ݸı��֪ͨ
			//uri:֪ͨ���͵���һ��URI�ϣ�����ע�������URI�ϵ����ݹ۲��߶������յ����֪ͨ
			getContext().getContentResolver().notifyChange(uri, null);
			
		}else if(um.match(uri)==2){
			db.insert("teacher", null, values);
			
			getContext().getContentResolver().notifyChange(uri, null);
		}else{
			throw new IllegalArgumentException("uri������");
		}
		return uri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int i = db.delete("person", selection, selectionArgs);
		return i;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int i =db.update("person", values, selection, selectionArgs);
		return i;
	}

}
