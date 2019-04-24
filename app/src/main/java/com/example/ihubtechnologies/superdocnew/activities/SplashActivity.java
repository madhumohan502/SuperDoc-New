package com.example.ihubtechnologies.superdocnew.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;

public class SplashActivity extends BaseActivity {
    private static int SPLASH_TIME_OUT = 3000;            // 3 seconds
    TextView supdoc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        supdoc = findViewById(R.id.supdoc);
//        supdoc.setTypeface(faceLight);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
//   5305 6200 0242 1945
     //  10/17   -   10/22
     //   078
            @Override
            public void run() {

//                if (sessionManager.getDOCTORID() != null) {
//                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
//                    startActivity(i);
//                } else {
//                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
//                    startActivity(i);
//                }
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
