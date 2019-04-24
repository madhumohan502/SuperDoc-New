package com.example.ihubtechnologies.superdocnew.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {
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
    @BindView(R.id.tv_hospital_location)
    TextView tvHospitalLocation;
    @BindView(R.id.thankyou)
    TextView thankyou;
    @BindView(R.id.id_layout2)
    LinearLayout idLayout2;
    @BindView(R.id.iv_verify_otp)
    LinearLayout ivVerifyOtp;
    @BindView(R.id.layout_gotoactualscreen)
    LinearLayout layoutGotoactualscreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        ButterKnife.bind(this);


        tvDoctorName.setText(sessionManager.getDOCTORNAME());
        tvHospitalName.setText(sessionManager.getHOSPITALNAME());
//        tvDoctorName.setTypeface(faceLight);
//        tvHospitalName.setTypeface(faceLight);
//        youconnected.setTypeface(faceLight);
//        tvHospitalLocation.setTypeface(faceLight);
//        thankyou.setTypeface(faceLight);
//        hello.setTypeface(faceLight);

    }

    @OnClick(R.id.iv_verify_otp)
    public void onViewClicked() {
        Intent i = new Intent(HomeActivity.this,DoctorSessionActivity.class);
        startActivity(i);
//        Intent i = new Intent(HomeActivity.this,AllAppointmentsActivity.class);
//        startActivity(i);
    }
}
