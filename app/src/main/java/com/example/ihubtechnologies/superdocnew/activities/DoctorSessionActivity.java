package com.example.ihubtechnologies.superdocnew.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.adapters.DoctorSessionAdapter;
import com.example.ihubtechnologies.superdocnew.pojos.response.DoctorSessionResponse;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorSessionActivity extends BaseActivity {
    RecyclerView rview;
    DoctorSessionAdapter doctorSessionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_session_activity);
        rview = findViewById(R.id.rview);
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
                    doctorSessionAdapter = new DoctorSessionAdapter(DoctorSessionActivity.this, doctorSessionResponses);
                    rview.setAdapter(doctorSessionAdapter);
                    doctorSessionAdapter.notifyDataSetChanged();
                } else {
                    showAlertDialog("failed");
                }
            }

            @Override
            public void onFailure(Call<List<DoctorSessionResponse>> call, Throwable t) {
                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

}
