package com.superphonebook.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.superphonebook.R;
import com.superphonebook.SuperPhoneBookApplication;
import com.superphonebook.model.Person;
import com.superphonebook.utils.StringUtil;

public class NewPeopleActivity extends Activity implements OnClickListener {
    private EditText username;
    private EditText mobilephone;
    private EditText unit_number;
    private EditText family_number;
    private EditText unitname;
    private EditText address;
    private EditText email;
    private EditText remark;

    private Button saveBtn;
    private Button cancelBtn;

    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.newpeople);

	username = (EditText) findViewById(R.id.username);
	mobilephone = (EditText) findViewById(R.id.mobilephone);
	unit_number = (EditText) findViewById(R.id.unit_number);
	family_number = (EditText) findViewById(R.id.family_number);
	unitname = (EditText) findViewById(R.id.unitname);
	address = (EditText) findViewById(R.id.address);
	email = (EditText) findViewById(R.id.email);
	remark = (EditText) findViewById(R.id.remark);

	saveBtn = (Button) findViewById(R.id.bt_save);
	cancelBtn = (Button) findViewById(R.id.bt_cancel);

	saveBtn.setOnClickListener(this);
	cancelBtn.setOnClickListener(this);

	String personName = this.getIntent().getStringExtra("personName");
	if (personName != null) {
	    setPersonToText(((SuperPhoneBookApplication) getApplication())
		    .getPersonService().getPersonByName(personName));
	    username.setEnabled(false);
	}
    }

    public void onClick(View v) {
	switch (v.getId()) {
	case R.id.bt_save:
	    Person p = getPersonfromText();
	    if (StringUtil.isNotBlank(p.getName())
		    && StringUtil.isNotBlank(p.getNumber())) {
		((SuperPhoneBookApplication) getApplication())
			.getPersonService().saveOrUpdatePerson(p);
		Intent intent = new Intent(NewPeopleActivity.this,
			UserActivity.class);
		intent.putExtra("personName", p.getName());
		startActivity(intent);
	    } else {
		Toast.makeText(this, "名称和手机号码不能为空", Toast.LENGTH_LONG).show();
	    }
	    break;
	case R.id.bt_cancel:
	    cancel();
	    break;
	default:
	    break;
	}
    }

    private void cancel() {
    }

    private Person getPersonfromText() {
	String username = this.username.getText().toString();
	String mobilephone = this.mobilephone.getText().toString();
	String unit_number = this.unit_number.getText().toString();
	String family_number = this.family_number.getText().toString();
	String unitname = this.unitname.getText().toString();
	String address = this.address.getText().toString();
	String email = this.email.getText().toString();
	String remark = this.remark.getText().toString();

	Person p = new Person();
	p.setName(username);
	p.setNumber(mobilephone);
	p.setUnitNumber(unit_number);
	p.setFamilyNumber(family_number);
	p.setUnitName(unitname);
	p.setAddress(address);
	p.setEmail(email);
	p.setRemark(remark);

	return p;
    }

    private void setPersonToText(Person p) {
	this.username.setText(p.getName());
	this.mobilephone.setText(p.getNumber());
	this.unit_number.setText(p.getUnitName());
	this.family_number.setText(p.getFamilyNumber());
	this.unitname.setText(p.getUnitNumber());
	this.address.setText(p.getAddress());
	this.email.setText(p.getEmail());
	this.remark.setText(p.getRemark());
    }

}
