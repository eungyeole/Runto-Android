package com.example.runto;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Toast toast;
    private long backKeyPressedTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button login=(Button) findViewById(R.id.login);
        TextView id = (TextView)findViewById(R.id.id);
        TextView password = (TextView)findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (!id.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    SharedPreferences pref = getSharedPreferences("userinfo",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("id", id.getText().toString());
                    editor.putString("password",password.getText().toString());
                    editor.commit();
                    finish();
                }
            }
        });
    }
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            ActivityCompat.finishAffinity(this);
            System.exit(0);
        }
    }
}