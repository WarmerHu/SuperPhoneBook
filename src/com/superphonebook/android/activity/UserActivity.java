package com.superphonebook.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.superphonebook.R;

public class UserActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user);
		String personName = this.getIntent().getStringExtra("personName");
		Toast.makeText(this, personName, Toast.LENGTH_LONG).show();
	}
}
