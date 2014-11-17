package com.superphonebook.android.activity;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.superphonebook.R;
import com.superphonebook.SuperPhoneBookApplication;
import com.superphonebook.map.comparator.BiHuaComparator;
import com.superphonebook.service.IPersonService;

public class MainActivity extends Activity implements OnClickListener, TextWatcher {
    
    private ListView nameListView;
    private Button newPeopleButton;
    private EditText searchText;
    
    private List<String> nameList;
    private IPersonService personService;
    private ArrayAdapter<String> myArrayAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	personService = ((SuperPhoneBookApplication) getApplication())
		.getPersonService();
	nameListView = (ListView) findViewById(R.id.lv_userlist);
	newPeopleButton = (Button) findViewById(R.id.newpeople);
	searchText = (EditText) findViewById(R.id.search);
	nameList = personService.getDefaultNameList();
	myArrayAdapter = new ArrayAdapter<String>(this,
		R.layout.person_name_list_item, nameList);
	nameListView.setAdapter(myArrayAdapter);
	searchText.setHint("搜索" + nameList.size() + "个联系人");
	myArrayAdapter.notifyDataSetChanged();
	
	newPeopleButton.setOnClickListener(this);
	nameListView.setOnItemClickListener(new OnItemClickListener() {

	    public void onItemClick(AdapterView<?> adapterView, View view,
		    int i, long l) {
		Intent intent = new Intent(MainActivity.this,
			UserActivity.class);
		intent.putExtra("personName", nameList.get(i));
		startActivity(intent);
	    }

	});
	
	searchText.addTextChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.activity_main, menu);
	return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
	switch(item.getItemId()) {
	case R.id.sortbybihua:
	    Collections.sort(nameList, BiHuaComparator.getBiHuaComparator());
	    myArrayAdapter.notifyDataSetChanged();
	    break;
	}
        return super.onMenuItemSelected(featureId, item);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        nameList.clear();
	nameList.addAll(personService.getDefaultNameList());
        myArrayAdapter.notifyDataSetChanged();
    }

    public void onClick(View arg0) {
	Intent intent02 = new Intent(MainActivity.this, NewPeopleActivity.class);
	startActivity(intent02);
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
	nameList.clear();
	nameList.addAll(personService.findNameListbyString(s.toString()));
	myArrayAdapter.notifyDataSetChanged();
    }
    
    public void afterTextChanged(Editable arg0) {
    }

    public void beforeTextChanged(CharSequence s, int start, int count,
	    int after) {
    }
}
