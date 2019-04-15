package com.example.ihubtechnologies.superdocnew.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.supdoc2)
    TextView supdoc2;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.iv_get_otp)
    ImageView ivGetOtp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        supdoc2 = findViewById(R.id.supdoc2);
        supdoc2.setTypeface(faceLight);

    }

    @OnClick(R.id.iv_get_otp)
    public void onViewClicked() {
        Intent i = new Intent(LoginActivity.this,OtpActivity.class);
        i.putExtra("mobile",etMobile.getText().toString());
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
