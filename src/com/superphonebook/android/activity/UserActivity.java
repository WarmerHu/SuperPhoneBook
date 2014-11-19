package com.superphonebook.android.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.superphonebook.R;
import com.superphonebook.SuperPhoneBookApplication;
import com.superphonebook.model.Person;
import com.superphonebook.utils.StringUtil;

public class UserActivity extends Activity implements OnClickListener, OnTouchListener {

    private Person person;
    private TextView phoneTextView;
    private TextView nameTextView;
    private LinearLayout detailLayout;

    private Button editBtn;
    private Button sendBtn;

    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.user);

	String personName = this.getIntent().getStringExtra("personName");
	person = ((SuperPhoneBookApplication) getApplication())
		.getPersonService().getPersonByName(personName);

	detailLayout = (LinearLayout) findViewById(R.id.detaillayout);
	
	phoneTextView = (TextView) findViewById(R.id.phonetextview);
	nameTextView = (TextView) findViewById(R.id.nametextview);
	phoneTextView.setText(person.getNumber());
	nameTextView.setText(person.getName());
	
	addDetailTextView("单位电话",person.getUnitNumber());
	addDetailTextView("家庭电话", person.getFamilyNumber());
	addDetailTextView("家庭地址", person.getAddress());
	addDetailTextView("邮箱", person.getEmail());
	addDetailTextView("单位名称", person.getUnitName());
	addDetailTextView("备注", person.getRemark());

	editBtn = (Button) findViewById(R.id.bt_edit);
	sendBtn = (Button) findViewById(R.id.bt_send);
	editBtn.setOnClickListener(this);
	sendBtn.setOnClickListener(this);
	
	phoneTextView.setOnTouchListener(this);
    }

    private void addDetailTextView(String text,String s) {
	if(StringUtil.isNotBlank(s)) {
	    TextView v = new TextView(this);
	    v.setTextAppearance(this, R.style.detailitem);
	    v.setText(s);
	    detailLayout.addView(v);
	}
    }

    public void onClick(View v) {
	switch (v.getId()) {
	case R.id.bt_edit:
	    Intent intent = new Intent(UserActivity.this,
		    NewPeopleActivity.class);
	    intent.putExtra("personName", person.getName());
	    startActivity(intent);
	    break;
	case R.id.bt_send:
	    sendMessage();
	    break;
	    default:
		Toast.makeText(this, "你怎么可能按到这里来。。", Toast.LENGTH_LONG).show();
	}
    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View v, MotionEvent m) {
	String number = phoneTextView.getText().toString();
	Intent intent = new Intent();
	intent.setAction(Intent.ACTION_CALL);
	intent.setData(Uri.parse("tel:" + number));
	startActivity(intent);
	return false;
    }
    
    public void sendMessage() {
	String number = phoneTextView.getText().toString();
	Intent sendIntent = new Intent();
	sendIntent.setAction(Intent.ACTION_SENDTO);
	sendIntent.setData(Uri.parse("smsto:" + number));
	startActivity(sendIntent);
    }
}
