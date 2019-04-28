package com.randroid.madam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.randroid.madam.SpywareChecker.LoadUrl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class AdminUpload extends Activity {

	EditText tit,pack,msg;
	String tit1, msg1;
	ArrayList<String> mobileArray;
	JSONObject obj;
	String title, message, n;
	ImageView up;
	String url="http://192.168.1.13/spyware/adminupload.php";
	String malwareList="http://192.168.1.13/spyware/listDefect.php";
	ProgressDialog pd;
	Button exit;
	ListView apkList;
	ArrayAdapter<String> arrayAdapter;
	
	ArrayList<HashMap<String, String>> list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_upload);
		
		ListMalware listMalware =new ListMalware();
		listMalware.execute(malwareList);
		mobileArray = new ArrayList<String>();
		apkList = (ListView) findViewById(R.id.listView1);
		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
		         R.layout.activity_listview, mobileArray);
	
		apkList.setAdapter(adapter);
		
		exit= (Button) findViewById(R.id.logout);
		
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(AdminUpload.this, MainActivity.class);
				startActivity(i);
			}
		});
		
		tit=(EditText) findViewById(R.id.title);
		pack=(EditText) findViewById(R.id.pack);
		msg=(EditText) findViewById(R.id.msg);
		
		up=(ImageView) findViewById(R.id.upload);
		
		up.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(tit.getText().toString().equals("")||pack.getText().toString().equals("")||msg.getText().toString().equals("")){
					Toast.makeText(AdminUpload.this, "Enter all the fields", Toast.LENGTH_LONG).show();
				}else{					
					LoadUrl l=new LoadUrl();
					l.execute(url);
				}
			}
		});
		
	}
	

	public class LoadUrl extends AsyncTask<String, String, String>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(AdminUpload.this);
			pd.setMessage("Loading...");
			pd.setMax(100);
			pd.setCancelable(false);
			pd.setIndeterminate(false);
			pd.show();
		}

		

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			//na,us,pa,se,lo,em,mo
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("tit",tit.getText().toString()));
			param.add(new BasicNameValuePair("pck",pack.getText().toString()));
			param.add(new BasicNameValuePair("msg",msg.getText().toString()));	
			param.add(new BasicNameValuePair("sug","no"));	
			
			JSONParser jp=new JSONParser();
			JSONObject obj=jp.makeHttpRequest(url, "POST", param);
			System.out.println(obj);
			Log.d("JSON", obj.toString());
			try {
				final int s=obj.getInt("success");
				Log.d("int", ""+s);
				runOnUiThread( new Runnable() {
					public void run() {
						if(s==1)
						{
						Toast.makeText(AdminUpload.this, "Created Successfully", Toast.LENGTH_SHORT).show();
						Intent i=new Intent(AdminUpload.this, AdminUpload.class);
						startActivity(i);
						}
						else
						{
							Toast.makeText(AdminUpload.this, "Failed to Create", Toast.LENGTH_SHORT).show();
							tit.setText("");
							pack.setText("");
							msg.setText("");
							
						}
					}
				});
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(pd!=null && pd.isShowing())
				pd.dismiss();
		}
		
	}
	public class ListMalware extends AsyncTask<String, String, String>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(AdminUpload.this);
			pd.setTitle("Please Wait...");
			pd.setMessage("Checking for malware..!");
			pd.setMax(100);
			pd.setCancelable(true);
			pd.setIndeterminate(false);
			pd.show();
		}

		

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> param=new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("x", "com.example.calcspy"));			
			list=new ArrayList<HashMap<String,String>>();
			JSONParser jp=new JSONParser();
			obj=jp.makeHttpRequest(malwareList, "GET", param);
			System.out.println(obj.toString());
			Log.d("JSON", obj.toString());
			try {
				JSONArray arr=obj.getJSONArray("product");
				for(int i=0;i<arr.length();i++)					
				{
					JSONObject js=arr.getJSONObject(i);				
	                tit1 = js.getString ("tit");	
	                mobileArray.add(tit1);
	                msg1= js.getString("msg");
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						if(obj.getInt("success")==1){
							Toast.makeText(AdminUpload.this, "Malware Found..!", 200).show();
						}else{
							Toast.makeText(AdminUpload.this, "Malware not Found..!", 200).show();
						}
						
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(pd!=null && pd.isShowing())
				pd.dismiss();
		
		}
		
	}

}

