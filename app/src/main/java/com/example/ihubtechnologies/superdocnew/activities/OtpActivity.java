package com.example.ihubtechnologies.superdocnew.activities;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.pojos.request.OtpVerificationRequest;
import com.example.ihubtechnologies.superdocnew.pojos.response.OtpVerificationResponse;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends BaseActivity implements View.OnClickListener{
    String mobile;
    EditText et_1,et_2,et_3,et_4;
    ImageView iv_verify_otp;
    private BroadcastReceiver receiver;
    String message;
    TextView supdoc3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp2);
        ButterKnife.bind(this);
        mobile = getIntent().getExtras().getString("mobile");
        supdoc3 = findViewById(R.id.supdoc3);
        supdoc3.setTypeface(faceLight);

        isPermissionGranted();

        mobile = getIntent().getExtras().getString("mobile");
        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        et_3 = findViewById(R.id.et_3);
        et_4 = findViewById(R.id.et_4);
        iv_verify_otp = findViewById(R.id.iv_verify_otp);


        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase("otp")) {
                    message = intent.getStringExtra("message");
                    final String message1 = intent.getStringExtra("message1");
                    final String message2 = intent.getStringExtra("message2");
                    final String message3 = intent.getStringExtra("message3");
                    final String message4 = intent.getStringExtra("message4");
                    et_1.setText(message1);
                    et_2.setText(message2);
                    et_3.setText(message3);
                    et_4.setText(message4);
                    //Do whatever you want with the code here
                }
            }
        };

        iv_verify_otp.setOnClickListener(this);


    }

    private boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) +
                    checkSelfPermission(Manifest.permission.READ_SMS) +
                    checkSelfPermission(Manifest.permission.SEND_SMS) +
                    checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.ACCESS_NETWORK_STATE}, 1);
                return false;
            }
        }
        else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(OtpActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();

                } else {

                }
                return;
            }
        }
    }

//    @OnClick(R.id.iv_verify_otp)
//    public void onViewClicked() {
//        moveToConformationActivity();
//    }

//    private void moveToConformationActivity() {
//        Intent i = new Intent(OtpActivity.this, HomeActivity.class);
//        startActivity(i);
//        finish();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_verify_otp:
//                Intent i = new Intent(OtpActivity.this,HomeActivity.class);
//                startActivity(i);
                final OtpVerificationRequest otpVerificationRequest = new OtpVerificationRequest(mobile, message);
                Call<OtpVerificationResponse> call = serviceCalls.doVerifyOTP(otpVerificationRequest);
                showDialog();
                call.enqueue(new Callback<OtpVerificationResponse>() {
                    @Override
                    public void onResponse(Call<OtpVerificationResponse> call, Response<OtpVerificationResponse> response) {
                        closeDialog();
                        if (response.code() == 200) {
                            OtpVerificationResponse otpVerificationResponse = response.body();
                            sessionManager.setDOCTORID(otpVerificationResponse.getDoctorId());
                            Intent i = new Intent(OtpActivity.this,HomeActivity.class);
                            startActivity(i);

                        } else {
                            Toast.makeText(OtpActivity.this, "Please Enter Valid OTP", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<OtpVerificationResponse> call, Throwable t) {
                        closeDialog();
                        showAlertDialog(t.getMessage());
                    }
                });
                break;
        }
    }
}
