package com.randroid.madam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends Activity {

Button btn;
EditText u, p;
String a = "admin";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_login);
	 btn = (Button) findViewById(R.id.btn);
	 u = (EditText) findViewById(R.id.user);
	 p = (EditText) findViewById(R.id.pass);
	 
	 btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(u.getText().toString().equals("")&&p.getText().toString().equals("")){
				Toast.makeText(AdminLoginActivity.this, "Please enter the field..!", Toast.LENGTH_LONG).show();
				
			}else{
				if(u.getText().toString().equalsIgnoreCase(a)&&p.getText().toString().equalsIgnoreCase(a)){
					Toast.makeText(AdminLoginActivity.this, "Wellcome..!", Toast.LENGTH_LONG).show();
					Intent i=new Intent(AdminLoginActivity.this, AdminUpload.class);
					startActivity(i);
					finish();
				}else{
					u.setText("");
					p.setText("");
					Toast.makeText(AdminLoginActivity.this, "Incorect username or password..!", Toast.LENGTH_LONG).show();
					
				}
				
			}
		}
	});
	}
	@Override
	public void onBackPressed() {
		Intent i=new Intent(AdminLoginActivity.this, MainActivity.class);
		startActivity(i);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
