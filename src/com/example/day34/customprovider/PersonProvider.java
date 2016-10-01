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
	
	//创建URI匹配器
	static UriMatcher um = new UriMatcher(UriMatcher.NO_MATCH);
	//检测其他用户传入的uri与匹配器定义好的URI中，那条匹配
	static{
		um.addURI("text.person.provider", "person", 1);
		um.addURI("text.person.provider", "teacher", 2);
		um.addURI("text.person.provider", "person/#", 2);//#匹配任意数字
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
			//把uri末尾携带的数字取出来
			long id = ContentUris.parseId(uri);
			cursor =db.query("person", projection, "_id = ?", new String[]{id+""}, null, null, sortOrder, null);
		}else{
			throw new IllegalArgumentException("uri又有问题哦");
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
	//此方法供其他应用调用，用于往person数据库里插入数据
	//values：由其他应用传入，用于封装要插入的数据
	//uri:内容提供者的主机名，也就是地址
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		//使用uri匹配器匹配传入的uri
		if(um.match(uri)==1){
			db.insert("person", null, values);
			
			//发送数据改变的通知
			//uri:通知发送到哪一个URI上，所有注册在这个URI上的内容观察者都可以收到这个通知
			getContext().getContentResolver().notifyChange(uri, null);
			
		}else if(um.match(uri)==2){
			db.insert("teacher", null, values);
			
			getContext().getContentResolver().notifyChange(uri, null);
		}else{
			throw new IllegalArgumentException("uri有问题");
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
