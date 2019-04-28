package com.androidqa;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
public class AnimationView extends Activity {
/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    InputStream stream = null;
    try {
        stream = getAssets().open("Progress");
    } catch (IOException e) {
        e.printStackTrace();
    }
          GifWebView view = new GifWebView(this, "file:///android_asset /Progress");                 

    setContentView(view);
}
}