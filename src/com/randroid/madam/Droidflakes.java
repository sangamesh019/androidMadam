package com.randroid.madam;
/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;


@SuppressLint("NewApi")
public class Droidflakes extends Activity {

    FlakeView flakeView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.droidflakes);
        clearApplicationData();
        
    	Handler h = new Handler();
		h.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent ji=new Intent(Droidflakes.this,MainActivity.class);
				startActivity(ji);
				Toast.makeText(Droidflakes.this, "Scan Completed..!", 200).show();
				finish();
				
				Toast.makeText(Droidflakes.this, "Sucessfully Logged Out", 200).show();
			}
		}, 8000);
        
        
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        flakeView = new FlakeView(this);
        container.addView(flakeView);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        Button more = (Button) findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flakeView.addFlakes(flakeView.getNumFlakes());
            }
        });
        Button less = (Button) findViewById(R.id.less);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flakeView.subtractFlakes(flakeView.getNumFlakes() / 4);
            }
        });
        if (Integer.parseInt(Build.VERSION.SDK) >= Build.VERSION_CODES.HONEYCOMB) {
            HoneycombHelper.setup(this);
        }
    }

    private static final class HoneycombHelper {
        static void setup(final Droidflakes activity) {
            CheckBox accelerated = (CheckBox) activity.findViewById(R.id.acclerated);
            accelerated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    activity.flakeView.setLayerType(
                            isChecked ? View.LAYER_TYPE_NONE : View.LAYER_TYPE_SOFTWARE, null);
                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        flakeView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flakeView.resume();
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
               
                }
            }
        }
    }
public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }
}
