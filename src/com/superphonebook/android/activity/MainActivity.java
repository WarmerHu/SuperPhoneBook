package com.superphonebook.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.superphonebook.R;
import com.superphonebook.SuperPhoneBookApplication;
import com.superphonebook.service.IPersonService;

public class MainActivity extends Activity implements OnClickListener {
    private ListView nameListView;
    private Button newPeopleButton;
    private List<String> nameList = new ArrayList<String>();
    private IPersonService personService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	personService = ((SuperPhoneBookApplication) getApplication())
		.getPersonService();
	nameListView = (ListView) findViewById(R.id.lv_userlist);
	newPeopleButton = (Button) findViewById(R.id.newpeople);

	nameList = personService.getDefaultNameList();
	newPeopleButton.setOnClickListener(this);
	ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this,
		R.layout.person_name_list_item, nameList);
	nameListView.setAdapter(myArrayAdapter);
	nameListView.setOnItemClickListener(new OnItemClickListener() {

	    public void onItemClick(AdapterView<?> adapterView, View view,
		    int i, long l) {
		Intent intent = new Intent(MainActivity.this,
			UserActivity.class);
		intent.putExtra("personName", nameList.get(i));
		startActivity(intent);
	    }

	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.activity_main, menu);
	return true;
    }

    public void onClick(View arg0) {
	Intent intent02 = new Intent(MainActivity.this, NewPeopleActivity.class);
	startActivity(intent02);

    }

}
