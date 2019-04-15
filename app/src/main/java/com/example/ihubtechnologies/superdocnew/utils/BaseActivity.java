package com.example.ihubtechnologies.superdocnew.utils;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.network.InternetStatus;
import com.example.ihubtechnologies.superdocnew.network.ServiceCalls;
import com.example.ihubtechnologies.superdocnew.network.ServiceGenaretor;

public class BaseActivity extends AppCompatActivity {
    public Typeface faceLight;
    public InternetStatus internetStatus;
    public ServiceCalls client;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        faceLight = Typeface.createFromAsset(getAssets(), "Montserrat-Light.otf");
        internetStatus = new InternetStatus(BaseActivity.this);
//        client = ServiceGenaretor.createService(ServiceCalls.class);
    }

    @Override
    protected void onStart() {
        super.onStart();

        }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public final void showDialog() {
        dialog = new ProgressDialog(BaseActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getResources().getString(R.string.loading_text));
        dialog.setCancelable(false);
        dialog.show();
    }

    public final void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
