package com.example.day34;

import com.example.day34.backups.TestBackupsSms;
import com.example.day34.contacts.TestGetContacts;
import com.example.day34.customprovider.TestCustomProvider;
import com.example.day34.gainsms.TestGainSms;
import com.example.day34.insertcontacts.TestInsertContacts;
import com.example.day34.insertsms.TestInsertSms;
import com.example.day34.listenerobserver.TestListenerObserver;
import com.example.day34.observer.TestObserver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		findViewById(R.id.day34_b1).setOnClickListener(this);
		findViewById(R.id.day34_b2).setOnClickListener(this);
		findViewById(R.id.day34_b3).setOnClickListener(this);
		findViewById(R.id.day34_b4).setOnClickListener(this);
		findViewById(R.id.day34_b5).setOnClickListener(this);
		findViewById(R.id.day34_b6).setOnClickListener(this);
		findViewById(R.id.day34_b7).setOnClickListener(this);
		findViewById(R.id.day34_b8).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.day34_b1:
			startActivity(new Intent(MainActivity.this, TestCustomProvider.class));
			break;
		case R.id.day34_b2:
			startActivity(new Intent(MainActivity.this, TestGainSms.class));
			break;
		case R.id.day34_b3:
			startActivity(new Intent(MainActivity.this, TestBackupsSms.class));
			break;
		case R.id.day34_b4:
			startActivity(new Intent(MainActivity.this, TestInsertSms.class));
			break;
		case R.id.day34_b5:
			startActivity(new Intent(MainActivity.this, TestGetContacts.class));
			break;
		case R.id.day34_b6:
			startActivity(new Intent(MainActivity.this, TestInsertContacts.class));
			break;
		case R.id.day34_b7:
			startActivity(new Intent(MainActivity.this, TestObserver.class));
			break;
		case R.id.day34_b8:
			startActivity(new Intent(MainActivity.this, TestListenerObserver.class));
			break;
		default:
			break;
		}
	}
}
