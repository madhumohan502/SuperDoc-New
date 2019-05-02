package com.example.ihubtechnologies.superdocnew.activities;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    public EditText et_1, et_2, et_3, et_4;
    ImageView iv_verify_otp;
    private BroadcastReceiver receiver;
    String message, otpMsg;
    TextView supdoc3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp2);
       // new CommentKeyBoardFix(this);
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


        et_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_1.getText().toString().length() == 1) {
                    et_2.requestFocus();
                }
            }
        });
        et_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_2.getText().toString().length() == 1) {
                    et_3.requestFocus();
                }else if(et_2.getText().toString().length() == 0)
                    et_1.requestFocus();
            }
        });
        et_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_3.getText().toString().length() == 1) {
                    et_4.requestFocus();
                }else if(et_3.getText().toString().length() == 0)
                    et_2.requestFocus();
            }
        });
        et_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_4.getText().toString().length() == 0) {
                    et_3.requestFocus();
                }
            }
        });

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
                    Toast.makeText(OtpActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
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
//1205
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_verify_otp:
//                Intent i = new Intent(OtpActivity.this,HomeActivity.class);
//                startActivity(i);
                otpMsg = et_1.getText().toString() + et_2.getText().toString() + et_3.getText().toString() + et_4.getText().toString();
                final OtpVerificationRequest otpVerificationRequest = new OtpVerificationRequest(mobile, otpMsg);
                Log.d("otprequest", otpVerificationRequest.toString() + "\n" + message);
                Call<OtpVerificationResponse> call = serviceCalls.doVerifyOTP(otpVerificationRequest);
                showDialog();
                call.enqueue(new Callback<OtpVerificationResponse>() {
                    @Override
                    public void onResponse(Call<OtpVerificationResponse> call, Response<OtpVerificationResponse> response) {
                        closeDialog();
                        if (response.code() == 200) {
                            OtpVerificationResponse otpVerificationResponse = response.body();
                            sessionManager.setDOCTORID(otpVerificationResponse.getDoctorId());
                            sessionManager.setDOCTORNAME(otpVerificationResponse.getDoctorName());
                            sessionManager.setHOSPITALNAME(otpVerificationResponse.getOrganization());
                            Intent i = new Intent(OtpActivity.this,HomeActivity.class);
                            i.putExtra("doctorname",otpVerificationResponse.getDoctorName());
                            i.putExtra("hospitalname",otpVerificationResponse.getOrganization());
                            startActivity(i);

                        } else {
                            showAlertDialog("Error :"+response.code());
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
    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
