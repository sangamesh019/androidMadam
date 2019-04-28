package com.randroid.madam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.randroid.madam.AdminUpload.LoadUrl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SpywareChecker extends Activity {

	PackageManager packageManager;
	String n,tit,msg;
	TextView up;
	Button spchck;
	ProgressDialog pd;
	//String url="http://10.0.2.2/Delta/updates.php";
	String url="http://192.168.1.13/spyware/spydata.php";
	ArrayList<HashMap<String, String>> list;
	JSONObject obj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spyware_checker);
		n = getIntent().getExtras().getString("name").toString();
		up =  (TextView) findViewById(R.id.upt);		
		spchck=(Button) findViewById(R.id.spware_check);
		up.setText(n);
		
		spchck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				LoadUrl l=new LoadUrl();
				l.execute(url);
				
			}
		});
	}
	
	@SuppressLint("NewApi")
	public void alertD()
	{
		
		AlertDialog.Builder builder=new AlertDialog.Builder(SpywareChecker.this, AlertDialog.THEME_TRADITIONAL);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle(tit);
		builder.setMessage(msg+""+"Yes..! To view graph, No to Perform Full Scan");
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("+ve", ""+which);
				EffortChart effort = new EffortChart();
	            Intent effortIntent = effort.getIntent(SpywareChecker.this);
	            startActivity(effortIntent);
			}
		});
		
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("-ve", ""+which);
				finish();
			}
		});
		
		builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("neutral", ""+which);
				dialog.cancel();
			}
		});
		
		
		AlertDialog ad=builder.create();
		ad.show();
	}
	
	
	@SuppressLint("NewApi")
	public void alertD1()
	{
		
		AlertDialog.Builder builder=new AlertDialog.Builder(SpywareChecker.this, AlertDialog.THEME_TRADITIONAL);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("MADAM Alert");
		builder.setMessage("Do you want to do Run Full Scan");
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("+ve", ""+which);
				
				 Intent i = new Intent(SpywareChecker.this, Trojandetection.class);
				 startActivity(i);
				Toast.makeText(SpywareChecker.this, "Thankyou for using MADAM", 2000).show();
			}
		});
		
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("-ve", ""+which);
				finish();
			}
		});
		
		builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("neutral", ""+which);
				dialog.cancel();
			}
		});
		
		
		AlertDialog ad=builder.create();
		ad.show();
	}
	
	private boolean isSystemPackage(PackageInfo pkgInfo) {
		return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
				: false;
	}

	@SuppressLint("NewApi")
	public void suggest()
	{
		packageManager = getPackageManager();
		List<PackageInfo> packageList = packageManager
				.getInstalledPackages(PackageManager.GET_PERMISSIONS);
		final ArrayList<Applist> test = new ArrayList<Applist>();

		int count =0;
		ArrayList<String> permissionsList = new ArrayList<String>();
				try {
					PackageInfo p = packageManager.getPackageInfo(n, packageManager.GET_PERMISSIONS);
					String[] permissions = null;
					if(p.requestedPermissions != null){
						 permissions = p.requestedPermissions;
						 for(String per : permissions){
							 System.out.println(per);
							 permissionsList.add(per);
						 }
//						 permissionsList = (ArrayList<String>) Arrays.asList(permissions);
					}
					
					if(permissionsList.contains("android.permission.CHANGE_NETWORK_STATE")){
						System.out.println("i m here");
						count = count +1;
					} 
					if(permissionsList.contains("android.permission.READ_EXTERNAL_STORAGE")){
						System.out.println("i m here");
						count = count +1;
					}
					
					if(permissionsList.contains("android.permission.ACCESS_FINE_LOCATION")){
						System.out.println("i m here");
						count = count +1;
					}
					if(permissionsList.contains("android.permission.CAMERA")){
						System.out.println("i m here");
						count = count +1;
					}
					if(permissionsList.contains("android.permission.READ_CONTACTS")){
						System.out.println("i m here");
						count = count +1;
					}
					
					if(permissionsList.contains("android.permission.BATTERY_STATS")){
						System.out.println("i m here");
						count = count +1;
					}
					if(permissionsList.contains("android.permission.SEND_SMS")){
						System.out.println("i m here");
						count = count +1;
					}
					if(permissionsList.contains("android.permission.GET_ACCOUNTS")){
						System.out.println("i m here");
						count = count +1;
					}
					if(permissionsList.contains("android.permission.USE_CREDENTIALS")){
						System.out.println("i m here");
						count = count +1;
					}
					if(permissionsList.contains("android.permission.INSTALL_PACKAGES")){
						System.out.println("i m here");
						count = count +1;
					}
					
					
					
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		if(count<1){
			
		}else{
		
		AlertDialog.Builder builder=new AlertDialog.Builder(SpywareChecker.this, AlertDialog.THEME_TRADITIONAL);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("MADAM Alert");
		builder.setMessage("Do you want to suggest admin");
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("+ve", ""+which);
				
				SuggestUser su = new SuggestUser();
				su.execute("http://192.168.1.13/spyware/adminupload.php");
				Toast.makeText(SpywareChecker.this, "Thankyou for suggestion", 2000).show();
			}
		});
		
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("-ve", ""+which);
				finish();
			}
		});
		
		builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("neutral", ""+which);
				dialog.cancel();
			}
		});
		
		
		AlertDialog ad=builder.create();
		ad.show();
		}
	}

	public class LoadUrl extends AsyncTask<String, String, String>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(SpywareChecker.this);
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
			param.add(new BasicNameValuePair("u", n));			
			list=new ArrayList<HashMap<String,String>>();
			JSONParser jp=new JSONParser();
			obj=jp.makeHttpRequest(url, "GET", param);
			Log.d("JSON", obj.toString());
			try {
				JSONArray arr=obj.getJSONArray("product");
				for(int i=0;i<arr.length();i++)					
				{
					JSONObject js=arr.getJSONObject(i);	
					if(js.getString ("sub").equals("no")){
		                tit = js.getString ("tit");	
		                msg= js.getString("msg");
					}
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
							alertD();
							Toast.makeText(SpywareChecker.this, "Malware Found..!", 200).show();
													
						}else{
							
//							alertD1();
							suggest();
							Toast.makeText(SpywareChecker.this, "Malware Found..!", 200).show();
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
	
	public class SuggestUser extends AsyncTask<String, String, String>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(SpywareChecker.this);
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
			param.add(new BasicNameValuePair("tit","title"));
			param.add(new BasicNameValuePair("pck",n));
			param.add(new BasicNameValuePair("msg","suggestion from user"));
			param.add(new BasicNameValuePair("sug","yes"));
			
			JSONParser jp=new JSONParser();
			JSONObject obj=jp.makeHttpRequest("http://192.168.1.13/spyware/adminupload.php", "POST", param);
			System.out.println(obj);
			Log.d("JSON", obj.toString());
			try {
				final int s=obj.getInt("success");
				Log.d("int", ""+s);
				runOnUiThread( new Runnable() {
					public void run() {
						if(s==1)
						{
						Toast.makeText(SpywareChecker.this, "Suggested Successfully", Toast.LENGTH_SHORT).show();
						Intent i=new Intent(SpywareChecker.this, ApkListActivity.class);
						startActivity(i);
						}
						else
						{
							Toast.makeText(SpywareChecker.this, "Failed to Create", Toast.LENGTH_SHORT).show();
							
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
}
