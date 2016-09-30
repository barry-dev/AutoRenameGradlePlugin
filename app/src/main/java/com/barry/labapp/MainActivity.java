package com.barry.labapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override protected void onResume() {
    super.onResume();
    TextView versionInfo = (TextView) findViewById(R.id.version_name);
    try {
      PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
      if (versionInfo != null) {
        versionInfo.setText(pInfo.versionName);
      }
    } catch (PackageManager.NameNotFoundException e) {
      Log.e(TAG, "onResume: Cannot get versionName", e);
    }
  }
}
