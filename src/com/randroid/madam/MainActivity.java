package com.randroid.madam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageView iv;
	ImageView add;
	ImageView use;
	ImageView about;
	ImageView credits;
	boolean doubleBackToExitPressedOnce = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		add = (ImageView) findViewById(R.id.ad_mageView1);
		use = (ImageView) findViewById(R.id.user_imageView1);
		about = (ImageView) findViewById(R.id.aboutUS);
		credits = (ImageView) findViewById(R.id.credits);
		
		about.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ii = new Intent(MainActivity.this, AboutAppActivity.class);
				startActivity(ii);
			}
		});
		
		credits.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ii = new Intent(MainActivity.this, CreditsAppActivity.class);
				startActivity(ii);
			}
		});
		
		use.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ii = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(ii);
			}
		});
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,
						AdminLoginActivity.class);
				startActivity(i);
			}
		});

	}

	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
			return;
		}

		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Please click BACK again to exit",
				Toast.LENGTH_SHORT).show();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;
			}
		}, 2000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
