package com.superphonebook.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.superphonebook.R;
import com.superphonebook.SuperPhoneBookApplication;
import com.superphonebook.model.Person;

public class UserActivity extends Activity {
    
    private Person person;
    private TextView phoneTextView;
    private TextView nameTextView;
    
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.user);
	
	String personName = this.getIntent().getStringExtra("personName");
	person = ((SuperPhoneBookApplication) getApplication())
		.getPersonService().getPersonByName(personName);
	
	phoneTextView = (TextView) findViewById(R.id.phonetextview);
	nameTextView = (TextView) findViewById(R.id.nametextview);
	phoneTextView.setText(person.getNumber());
	nameTextView.setText(person.getName());
	
    }
}
