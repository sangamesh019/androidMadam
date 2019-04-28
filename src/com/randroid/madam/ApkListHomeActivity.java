package com.randroid.madam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ApkListHomeActivity extends Activity implements OnClickListener {

	Button exit;
	Button viewlist;
	Button fullScanHome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apk_list_home_activity);

		exit = (Button) findViewById(R.id.logoutUser);
		viewlist = (Button) findViewById(R.id.ViewlistHome);
		fullScanHome = (Button) findViewById(R.id.FullScanHome);

		
		exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ApkListHomeActivity.this,
						MainActivity.class);
				startActivity(i);
			}
		});

		viewlist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ApkListHomeActivity.this,
						ApkListActivity.class);
				startActivity(i);
			}
		});
		
		fullScanHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(ApkListHomeActivity.this, "Please click BACK again to exit",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
