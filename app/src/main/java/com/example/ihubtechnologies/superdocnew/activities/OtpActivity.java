package com.example.ihubtechnologies.superdocnew.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtpActivity extends BaseActivity {
    @BindView(R.id.supdoc3)
    TextView supdoc3;
    @BindView(R.id.iv_verify_otp)
    ImageView ivVerifyOtp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        supdoc3 = findViewById(R.id.supdoc3);
        supdoc3.setTypeface(faceLight);
    }

    @OnClick(R.id.iv_verify_otp)
    public void onViewClicked() {
        moveToConformationActivity();
    }

    private void moveToConformationActivity() {
        Intent i = new Intent(OtpActivity.this, ConfirmationActivity.class);
        startActivity(i);
        finish();
    }
}
