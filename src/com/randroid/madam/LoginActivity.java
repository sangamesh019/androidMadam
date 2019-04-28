package com.randroid.madam;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText uname,pass;
	Button signin;
	String p,u;
	String aa = "admin";
	SQLiteDatabase db;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		uname=(EditText) findViewById(R.id.uname_editText1);
		pass=(EditText) findViewById(R.id.pass_editText2);
		signin=(Button) findViewById(R.id.signin_button1);
		
		tv=(TextView) findViewById(R.id.reg_tv);
		
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent ii = new Intent(LoginActivity.this,RegisterActivity.class);
         		startActivity(ii);
			}
		});
		
		signin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(uname.getText().toString().equals("")||pass.getText().toString().equals("")){
					
					Toast.makeText(LoginActivity.this, "Enter all fields..!", Toast.LENGTH_LONG).show();
				}else{	 
//					LoadUrl l = new LoadUrl();
//            		l.execute();
					 u = uname.getText().toString();
					 p = pass.getText().toString();
						if(uname.getText().toString().equals(aa)&& pass.getText().toString().equals(aa)){
							
							
						}else{
					
					 try{
					          db=openOrCreateDatabase("delta",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
					         
					        }catch(Exception exception){

					            exception.printStackTrace();

					        }try{
					        	 Cursor cc = db.rawQuery("select * from user where userid = '"+u+"' and pass = '"+p+"'", null);
							     cc.moveToFirst();
						            String temp="";					       
						            if (cc != null) {
						            	if(cc.getCount() > 0)
						            	{
						            	//return true;
						            		
						            
						            		Toast.makeText(LoginActivity.this, "Welcome..! "  +  u, Toast.LENGTH_LONG).show();
					            	        Intent i = new Intent(LoginActivity.this,ApkListActivity.class);
						            		startActivity(i);
						            	
						            	}else{
						            		 Intent ii = new Intent(LoginActivity.this,MainActivity.class);
							            		startActivity(ii);
						            		 Toast.makeText(LoginActivity.this, "Incorrect username or password..!", Toast.LENGTH_LONG).show();
						            	}
						            	}
						            //	return false;
						           
					        }catch(Exception exception){

					            exception.printStackTrace();

					        }
						
						 
						}
					 	
				}
			}
		});
	}

	//progress
		public class LoadUrl extends AsyncTask<String, String, String>
		{

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				ProgressDialog pd = new ProgressDialog(LoginActivity.this);
				pd.setTitle("Please Wait..");
				pd.setMessage("Logging....");
				pd.setMax(100);
				pd.setCancelable(true);
				pd.setIndeterminate(true);
				pd.show();
			}

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				return null;
			}
		}
		//end

}
