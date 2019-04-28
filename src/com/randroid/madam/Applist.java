package com.randroid.madam;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Applist {

	String good;
	String appName;
	Drawable image;
	
	
	public Drawable getImage() {
		return image;
	}
	public void setImage(Drawable drawable) {
		this.image = drawable;
	}
	public String getGood() {
		return good;
	}
	public void setGood(String good) {
		this.good = good;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}	
	
	
}
