package com.example.day34.customprovider;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase {
	public void test(){
		MyOpenHelper oh = new MyOpenHelper(getContext());
		oh.getWritableDatabase();
	}
}
