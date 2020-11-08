package com.example.runto;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment fragmentHome = new HomeFragment();
    private studyFragment fragmentStudy = new studyFragment();
    private SettingFragment fragmentSetting = new SettingFragment();
    private Toast toast;
    private long backKeyPressedTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = getSharedPreferences("userinfo",MODE_PRIVATE);
        String preId=pref.getString("id","");
        System.out.println(preId);
        if(preId.equals("")){
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }

        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        //WebView myWebView = (WebView) findViewById(R.id.webview);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId())
                {
                    case R.id.action_home:
                        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                        break;
                    case R.id.action_study:
                        transaction.replace(R.id.frameLayout, fragmentStudy).commitAllowingStateLoss();
                        //myWebView.loadUrl("http://10.156.147.199:3000/myroom");
                        break;
                    case R.id.action_setting:
                        transaction.replace(R.id.frameLayout, fragmentSetting).commitAllowingStateLoss();
                        //myWebView.loadUrl("http://10.156.147.199:3000/room/아이유");
                        break;
                }
                return true;
            }
        });
//        WebSettings webSettings = myWebView.getSettings();
//        myWebView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        webSettings.setJavaScriptEnabled(true);
//        myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        myWebView.getSettings().setSupportMultipleWindows(true);
//        myWebView.getSettings().setTextZoom(100);
//        myWebView.loadUrl("http://10.156.147.199:3000/glist");
    }
//    @Override
//    public void onBackPressed() {
//        WebView myWebView = (WebView) findViewById(R.id.webview);
//        if(myWebView.canGoBack()){
//            myWebView.goBack();
//        } else {
//
//            Toast.makeText(this,"'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
//        }
//    }
    public void onBackPressed() {
    if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
        backKeyPressedTime = System.currentTimeMillis();
        toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
        return;
    }
    if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
        finish();
        toast.cancel();
    }
}

}