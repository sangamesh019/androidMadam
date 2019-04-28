package com.randroid.madam;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	EditText n,u,p,m;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		n=(EditText) findViewById(R.id.name);
		u=(EditText) findViewById(R.id.userid);
		p=(EditText) findViewById(R.id.pass);
		m=(EditText) findViewById(R.id.ph);
		
		btn = (Button) findViewById(R.id.log);
		
		  btn.setOnClickListener(new OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
					
					if( n.getText().toString().equals("") && u.getText().toString().equals("")&& p.getText().toString().equals("")&& m.getText().toString().equals(""))
							{
						  Toast.makeText(RegisterActivity.this, "Enter all field..!",Toast.LENGTH_LONG).show();
					  }else{
						 
						  DBconnectors dbc = new DBconnectors(RegisterActivity.this);
						  
						  HashMap<String , String> map = new  HashMap<String, String>();
						  map.put("name", n.getText().toString());
						  map.put("userid", u.getText().toString());
						  map.put("pass", p.getText().toString());
						  map.put("mob", p.getText().toString());
						 
						  dbc.inser_row(map);
						  Toast.makeText(RegisterActivity.this, "Register Success..!", 200).show();				  
						  n.setText("");
						  u.setText("");
						  p.setText("");
						  m.setText("");
						
						  Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
						  startActivity(i);
					  }
				}
			});
		
	}

	

}
