package com.example.ihubtechnologies.superdocnew.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.pojos.response.LoginResponse;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        setContentView(R.layout.activity_login2);
        //new CommentKeyBoardFix(this);
        ButterKnife.bind(this);
        supdoc2 = findViewById(R.id.supdoc2);
        supdoc2.setTypeface(faceLight);

    }

    @OnClick(R.id.iv_get_otp)
    public void onViewClicked() {
        if (!etMobile.getText().toString().isEmpty()){
            Call<LoginResponse> call = serviceCalls.doLogin(etMobile.getText().toString());
            showDialog();
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    closeDialog();
                    if (response.code()==200){
                        LoginResponse loginResponse = response.body();
//                    Toast.makeText(LoginActivity.this, "OTP will fetch automatically", Toast.LENGTH_LONG).show();
                        Toast.makeText(LoginActivity.this, loginResponse.getMsg(), Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginActivity.this,OtpActivity.class);
                        i.putExtra("mobile",etMobile.getText().toString());
                        startActivity(i);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }else {
                        showAlertDialog("Error :"+response.code());
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    closeDialog();
                    showAlertDialog(t.getMessage());
                }
            });
        }else {
            Toast.makeText(LoginActivity.this,"please enter mobile number",Toast.LENGTH_LONG).show();
        }






//        Intent i = new Intent(LoginActivity.this,OtpActivity.class);
//        i.putExtra("mobile",etMobile.getText().toString());
//        startActivity(i);
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
