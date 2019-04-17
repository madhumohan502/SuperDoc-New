package com.example.ihubtechnologies.superdocnew.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.pojos.response.DoctorSessionResponse;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.thankyou)
    TextView thankyou;
    @BindView(R.id.id_layout2)
    LinearLayout idLayout2;
    @BindView(R.id.iv_verify_otp)
    ImageView ivVerifyOtp;
    @BindView(R.id.layout_gotoactualscreen)
    LinearLayout layoutGotoactualscreen;
    String doctorid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_verify_otp)
    public void onViewClicked() {
        Intent i = new Intent(HomeActivity.this,DoctorSessionActivity.class);
        startActivity(i);
    }
}
