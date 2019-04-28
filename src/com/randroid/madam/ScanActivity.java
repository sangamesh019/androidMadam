package com.randroid.madam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ScanActivity extends Activity {
	TextView a,s,d,aa,ss,dd;
	ImageView h,re;
    int iInternalCount;
    String sFileName = "trojan.txt";
    String sBody = "IZ—O7ãï©02ÈÈ¶P&ÀwÈÛ®E9Ëôð^HÔt“g÷-eãœ.!÷aUèÈmñ-ÑÚ†cÈÑƒ£oOÑ“ß¢”RtÎÝqÍŠ–$";
    File f;
	ProgressBar progressBar;
	int progressStatus = 0;
	TextView textView;
	Handler handler = new Handler();
	String importError;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		iInternalCount = 0;
		a=(TextView) findViewById(R.id.jc);
		aa=(TextView) findViewById(R.id.junkc);
		s=(TextView) findViewById(R.id.ac);
		ss=(TextView) findViewById(R.id.appc);
		d=(TextView) findViewById(R.id.pi);
		dd=(TextView) findViewById(R.id.privacyi);
	    h=(ImageView) findViewById(R.id.scan);
	    re=(ImageView) findViewById(R.id.re);
	  
	    progressBar = (ProgressBar) findViewById(R.id.progressBar1);
	    textView = (TextView) findViewById(R.id.textView1);
	    
	    	
	    h.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Start long running operation in a background thread
				  new Thread(new Runnable() {
				     public void run() {
				        while (progressStatus < 100) {
				           progressStatus += 5;
				    // Update the progress bar and display the 
				                         //current value in the text view
				    handler.post(new Runnable() {
				    public void run() {
				    	progressBar.setVisibility(1);
				    	progressBar.setProgress(progressStatus);
				    	textView.setText(progressStatus+"/"+progressBar.getMax());
				    	textView.setVisibility(0);
				       			                
			                //write internal 
			                iInternalCount=iInternalCount+1;
							//writeFileInternalStorage();
			                //generateNoteOnSD();
			                
								
				    }  
				        });
				        try {
				        	
				           // Sleep for 200 milliseconds. 
				                         //Just to display the progress slowly
				           Thread.sleep(250);
				        } catch (InterruptedException e) {
				           e.printStackTrace();
				        }
				     }
				  }
				     
				  }).start();
				showCustomAlert();
				generateNoteOnSD();
				
				
							
			}
		});
		
		re.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			    showResultAlert();
			     //DeleteNoteOnSD();
				Intent m=new Intent(ScanActivity.this,Droidflakes.class);
				startActivity(m);	
				DeleteNoteOnSD();
			}
		});
		
	}
	public void generateNoteOnSD(){
	    try
	    {
	        File root = new File(Environment.getExternalStorageDirectory(), "Madam");
	        if (!root.exists()) {
	            root.mkdirs();
	        }
	        File gpxfile = new File(root, sFileName);
	        FileWriter writer = new FileWriter(gpxfile);
	        writer.append(sBody);
	        writer.flush();
	        writer.close();
	        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
	        
	    }
	    catch(IOException e)
	    {
	         e.printStackTrace();
	         importError = e.getMessage();
	         
	    }
	   }  
	
	public void DeleteNoteOnSD(){
	    File root = new File(Environment.getExternalStorageDirectory(), "Madam");
		if (!root.exists()) {
		    root.mkdirs();
		}
		File gpxfile = new File(root, sFileName);
        gpxfile.delete();
  
	   }
	 public void showCustomAlert()
	    {
	         
	        Context context = getApplicationContext();
	        // Create layout inflator object to inflate toast.xml file
	        LayoutInflater inflater = getLayoutInflater();
	          
	        // Call toast.xml file for toast layout 
	        View toastRoot = inflater.inflate(R.layout.toast, null);	          
	        Toast toast = new Toast(context);	         
	        // Set layout to toast 
	        toast.setView(toastRoot);
	        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
	        toast.setDuration(10000);
	        toast.show();

	    }
	 
	 public void showResultAlert()
	    {
	         
	        Context context = getApplicationContext();
	        // Create layout inflator object to inflate toast.xml file
	        LayoutInflater inflater = getLayoutInflater();
	          
	        // Call toast.xml file for toast layout 
	        View toastRoot = inflater.inflate(R.layout.toast1, null);
	          
	        Toast toast1 = new Toast(context);
	         
	        // Set layout to toast 
	        toast1.setView(toastRoot);
	        toast1.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
	        toast1.setDuration(200);
	        toast1.show();
	         
	    }
}
