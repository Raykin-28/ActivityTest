package com.wwj.activitytest;

import com.wwj.utils.ExitApplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class ResultActivity extends Activity {
	private TextView textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		ExitApplication.getInstance().addActivity(this);
		textview = (TextView) findViewById(R.id.TextView02);
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		String name = b.getString("name");
		String[] projection = new String[] { People._ID, People.NAME,
				People.NUMBER };
		Uri contacts = People.CONTENT_URI;
		String[] args = { "%"+name+"%" };
		Cursor c = managedQuery(contacts, projection, "name like ?",args,
				People.NAME + " ASC");
//		if (c.moveToFirst()) {
//			String name1 = c.getString(1);
//			String number = c.getString(2);
//			textview.setText(name1 + ":" + number);
//		}
		while(c.moveToNext()){
			String name1 = c.getString(1);
			String number = c.getString(2);
			if("".equals(textview.getText().toString())|textview.getText().length()<=0){
				textview.setText(name1 + ":" + number+"\n");
			}else{
				textview.setText(textview.getText()+name1 + ":" + number+"\n");
			}
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
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
}
