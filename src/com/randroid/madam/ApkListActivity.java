package com.randroid.madam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.randroid.madam.AdminUpload.ListMalware;

import android.os.AsyncTask;
import android.os.Bundle;
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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class ApkListActivity extends Activity implements OnItemClickListener {

	PackageManager packageManager;
	ListView apkList, apkListAll;
	Button exit;
	Button viewlist;
	Button fullScan;
	String tit1, msg1, pck;
	static ArrayList<String> mobileArray = new ArrayList<String>();
	JSONObject obj;
	String malwareList = "http://192.168.1.13/spyware/listDefect.php";
	ProgressDialog pd;
	ArrayList<HashMap<String, String>> list;
	public List<PackageInfo> packageList1 = new ArrayList<PackageInfo>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		exit = (Button) findViewById(R.id.logoutUser);
		viewlist = (Button) findViewById(R.id.Viewlist);
		fullScan = (Button) findViewById(R.id.FullScan);

		exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ApkListActivity.this, MainActivity.class);
				startActivity(i);
			}
		});

		fullScan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alertD1();
			}
		});
//		mobileArray = 
		ListMalware listMalware = new ListMalware();
		listMalware.execute(malwareList);

		packageManager = getPackageManager();
		List<PackageInfo> packageList = packageManager
				.getInstalledPackages(PackageManager.GET_PERMISSIONS);
		final ArrayList<Applist> test = new ArrayList<Applist>();
		/* To filter out System apps */
		ArrayList<String> permissionsList = new ArrayList<String>();
		for (PackageInfo pi : packageList) {
			boolean b = isSystemPackage(pi);
			if (!b) {
				try {
					PackageInfo p = packageManager.getPackageInfo(pi.packageName, packageManager.GET_PERMISSIONS);
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
					}
					
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				packageList1.add(pi);
				Applist l = new Applist();
				
				if (mobileArray.contains(pi.packageName)) {
					l.setAppName(pi.packageName);
					l.setGood("Malicious");
					l.setImage(packageManager
							.getApplicationIcon(pi.applicationInfo));
					test.add(l);
				} 				
				
			}
		}

		AdapterApp adp = new AdapterApp(this, 0, test);

		apkList = (ListView) findViewById(R.id.applist);
		apkListAll = (ListView) findViewById(R.id.applistall);
		
		apkList.setAdapter(adp);
		// apkList.setOnItemClickListener(ApkListActivity.this);

		viewlist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				apkListAll.setAdapter(new ApkAdapter(ApkListActivity.this, packageList1, packageManager));

				apkListAll.setOnItemClickListener(ApkListActivity.this);
			}
		});
	}

	/**
	 * Return whether the given PackgeInfo represents a system package or not.
	 * User-installed packages (Market or otherwise) should not be denoted as
	 * system packages.
	 * 
	 * @param pkgInfo
	 * @return boolean
	 */
	private boolean isSystemPackage(PackageInfo pkgInfo) {
		return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
				: false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long row) {
		PackageInfo packageInfo = (PackageInfo) parent
				.getItemAtPosition(position);
		/*
		 * AppData appData = (AppData) getApplicationContext();
		 * appData.setPackageInfo(packageInfo);
		 * 
		 * Intent appInfo = new Intent(getApplicationContext(), ApkInfo.class);
		 * startActivity(appInfo);
		 */
		Toast.makeText(ApkListActivity.this, "" + packageInfo.packageName, 200)
				.show();
		Intent i = new Intent(ApkListActivity.this, SpywareChecker.class);
		i.putExtra("name", "" + packageInfo.packageName);
		startActivity(i);
	}

	@SuppressLint("NewApi")
	public void alertD1() {

		AlertDialog.Builder builder = new AlertDialog.Builder(
				ApkListActivity.this, AlertDialog.THEME_TRADITIONAL);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("MADAM Alert");
		builder.setMessage("Do you want to do Run Full Scan");

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("+ve", "" + which);

				Intent i = new Intent(ApkListActivity.this,
						Trojandetection.class);
				startActivity(i);
				Toast.makeText(ApkListActivity.this,
						"Thankyou for using MADAM", 2000).show();
			}
		});

		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.d("-ve", "" + which);
				finish();
			}
		});

		builder.setNeutralButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.d("neutral", "" + which);
						dialog.cancel();
					}
				});

		AlertDialog ad = builder.create();
		ad.show();
	}

	public class ListMalware extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(ApkListActivity.this);
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
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("x", "com.example.calcspy"));
			list = new ArrayList<HashMap<String, String>>();
			JSONParser jp = new JSONParser();
			obj = jp.makeHttpRequest(malwareList, "GET", param);
			System.out.println(obj.toString());
			Log.d("JSON", obj.toString());
			try {
				JSONArray arr = obj.getJSONArray("product");
				for (int i = 0; i < arr.length(); i++) {
					JSONObject js = arr.getJSONObject(i);
					tit1 = js.getString("tit");
					msg1 = js.getString("msg");
					pck = js.getString("pck");
					mobileArray.add(pck);
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
						if (obj.getInt("success") == 1) {
							Toast.makeText(ApkListActivity.this,
									"Malware Found..!", 200).show();
						} else {
							Toast.makeText(ApkListActivity.this,
									"Malware not Found..!", 200).show();
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
			if (pd != null && pd.isShowing())
				pd.dismiss();

		}

	}
}