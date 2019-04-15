package com.example.ihubtechnologies.superdocnew.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ihubtechnologies.superdocnew.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmationActivity extends AppCompatActivity {
    @BindView(R.id.hello)
    TextView hello;
    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;
    @BindView(R.id.id_layout1)
    LinearLayout idLayout1;
    @BindView(R.id.youconnected)
    TextView youconnected;
    @BindView(R.id.tv_hospital_name)
    TextView tvHospitalName;
    @BindView(R.id.thankyou)
    TextView thankyou;
    @BindView(R.id.id_layout2)
    LinearLayout idLayout2;
    @BindView(R.id.iv_verify_otp)
    ImageView ivVerifyOtp;
    @BindView(R.id.layout_gotoactualscreen)
    LinearLayout layoutGotoactualscreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_verify_otp)
    public void onViewClicked() {

    }
}
