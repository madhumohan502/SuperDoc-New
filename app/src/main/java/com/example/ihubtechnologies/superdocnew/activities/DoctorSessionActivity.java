package com.example.ihubtechnologies.superdocnew.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.adapters.DoctorSessionAdapter;
import com.example.ihubtechnologies.superdocnew.pojos.response.DoctorSessionResponse;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorSessionActivity extends BaseActivity  {
    //implements DoctorSessionAdapter.redirector
    RecyclerView rview;
    DoctorSessionAdapter doctorSessionAdapter;
TextView tvDoctorName;
TextView tvHello;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_session_activity);
        ButterKnife.bind(this);
        rview = findViewById(R.id.rview);
        tvDoctorName = findViewById(R.id.tv_doctor_name);
        tvDoctorName.setText(sessionManager.getDOCTORNAME());
        tvHello = findViewById(R.id.hello);
        tvHello.setTypeface(faceLight);
        tvDoctorName.setTypeface(faceLight);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rview.setLayoutManager(linearLayoutManager);
        rview.setHasFixedSize(true);
        getDoctorSessions();
    }

    private void getDoctorSessions() {
        Call<List<DoctorSessionResponse>> call = serviceCalls.getDoctorSessions(sessionManager.getDOCTORID());
        showDialog();
        call.enqueue(new Callback<List<DoctorSessionResponse>>() {
            @Override
            public void onResponse(Call<List<DoctorSessionResponse>> call, Response<List<DoctorSessionResponse>> response) {
                closeDialog();
                if (response.code() == 200) {
                    List<DoctorSessionResponse> doctorSessionResponses = response.body();
                    if (doctorSessionResponses.size()==0){
                        showAlertDialog("No Sessions Found");
                    }else {
                        doctorSessionAdapter = new DoctorSessionAdapter(DoctorSessionActivity.this, doctorSessionResponses);
                        rview.setAdapter(doctorSessionAdapter);
                        doctorSessionAdapter.notifyDataSetChanged();
                    }
                } else {
                    showAlertDialog("Error :"+response.code());
                }
            }

            @Override
            public void onFailure(Call<List<DoctorSessionResponse>> call, Throwable t) {
                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }
//
//    @Override
//    public void sendData(int a, CardView cardView, int count, String orgName, String time) {
//        Intent intent = new Intent(DoctorSessionActivity.this, AllAppointmentsActivity.class);
//        intent.putExtra("AppointmentsCount", String.valueOf(count));
//        intent.putExtra("OrganizationName",orgName);
//        intent.putExtra("SessionTime",time);
//
//        intent.putExtra("name", ViewCompat.getTransitionName(cardView));
//
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                DoctorSessionActivity.this,
//                cardView,
//                ViewCompat.getTransitionName(cardView));
//
//        ActivityCompat.startActivity(DoctorSessionActivity.this, intent, options.toBundle());
//
//    }
}
