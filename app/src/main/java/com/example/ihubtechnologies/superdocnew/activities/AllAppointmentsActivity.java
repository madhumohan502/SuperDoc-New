package com.example.ihubtechnologies.superdocnew.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.adapters.AllAppointmentsAdapter;
import com.example.ihubtechnologies.superdocnew.adapters.ConfirmedAppointmentsAdapter;
import com.example.ihubtechnologies.superdocnew.pojos.response.AllAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.ConfirmedAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;
import com.example.ihubtechnologies.superdocnew.utils.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAppointmentsActivity extends BaseActivity {
    public RecyclerView rview;
    public LinearLayoutManager linearLayoutManager;
    TextView tvAppointmentsCount, tvHospitalName, tvSessionTime;
    LinearLayout allAppointments, checkinAppointments, noshowAppointments, cancelAppointments;
    TextView allAppointmentsSize, checkinAppointmentsSize, noshowAppointmentsSize, cancelAppointmentsSize;
   public AllAppointmentsAdapter allAppointmentsAdapter;
   public ConfirmedAppointmentsAdapter confirmedAppointmentsAdapter;
    String AppointmentsCount, OrganizationName, SessionTime;
    int int_allAppointmentsSize, int_noshowAppointmentsSize, int_checkinAppointmentsSize, int_cancelAppointmentsSize;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_appointments_activity);

        AppointmentsCount = getIntent().getExtras().getString("AppointmentsCount");
        OrganizationName = getIntent().getExtras().getString("OrganizationName");
        SessionTime = getIntent().getExtras().getString("SessionTime");

        allAppointments = findViewById(R.id.allAppointments);
        allAppointmentsSize = findViewById(R.id.allAppointmentsSize);
        checkinAppointments = findViewById(R.id.checkinAppointments);
        checkinAppointmentsSize = findViewById(R.id.checkinAppointmentsSize);
        noshowAppointments = findViewById(R.id.noshowAppointments);
        noshowAppointmentsSize = findViewById(R.id.noshowAppointmentsSize);
        cancelAppointments = findViewById(R.id.cancelAppointments);
        cancelAppointmentsSize = findViewById(R.id.cancelAppointmentsSize);


        tvAppointmentsCount = findViewById(R.id.tv_appointments_count);
        tvHospitalName = findViewById(R.id.tv_hospital_name);
        tvSessionTime = findViewById(R.id.tv_session_time);

        tvAppointmentsCount.setTypeface(faceLight);
        tvHospitalName.setTypeface(faceLight);
        tvSessionTime.setTypeface(faceLight);

        tvAppointmentsCount.setText(AppointmentsCount);
        tvHospitalName.setText(OrganizationName);
        tvSessionTime.setText(SessionTime);

        rview = findViewById(R.id.rview);
        linearLayoutManager = new LinearLayoutManager(this);
        rview.setLayoutManager(linearLayoutManager);
        rview.setHasFixedSize(true);

        getAllAppointmentsSize();
        getlistOfConfirmedAppointmentsSize();
        getAllAppointments();


        allAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                getAllAppointments();
            }
        });
        checkinAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                getlistOfConfirmedAppointments();
            }
        });
        noshowAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                getlistOfConfirmedAppointments();
            }
        });
        cancelAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                getlistOfConfirmedAppointments();
            }
        });

    }


    private void getlistOfConfirmedAppointmentsSize() {
        Call<List<ConfirmedAppointmentsResponse>> call = serviceCalls.getListOfConfirmedAppointments(sessionManager.getDOCTORID());
//        showDialog();
        call.enqueue(new Callback<List<ConfirmedAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<ConfirmedAppointmentsResponse>> call, Response<List<ConfirmedAppointmentsResponse>> response) {
//                closeDialog();
                if (response.code() == 200) {
                    List<ConfirmedAppointmentsResponse> confirmedAppointmentsResponseList = response.body();
                    int_checkinAppointmentsSize = confirmedAppointmentsResponseList.size();
                    checkinAppointmentsSize.setText(String.valueOf(int_checkinAppointmentsSize));
                    noshowAppointmentsSize.setText(String.valueOf(int_checkinAppointmentsSize));
                    cancelAppointmentsSize.setText(String.valueOf(int_checkinAppointmentsSize));
                } else {
                    showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ConfirmedAppointmentsResponse>> call, Throwable t) {
//                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void getAllAppointmentsSize() {
        Call<List<AllAppointmentsResponse>> call = serviceCalls.getAllAppointments(sessionManager.getDOCTORID());
        showDialog();
        call.enqueue(new Callback<List<AllAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<AllAppointmentsResponse>> call, Response<List<AllAppointmentsResponse>> response) {
                closeDialog();
                if (response.code() == 200) {
                    List<AllAppointmentsResponse> allAppointmentsResponses = response.body();
                    int_allAppointmentsSize = allAppointmentsResponses.size();
                    Log.d("sizeeeee", String.valueOf(int_allAppointmentsSize));
                    allAppointmentsSize.setText(String.valueOf(int_allAppointmentsSize));

                } else {
                    showAlertDialog("Error :"+response.code());
                }
            }

            @Override
            public void onFailure(Call<List<AllAppointmentsResponse>> call, Throwable t) {
                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    public void getAllAppointments() {
        Call<List<AllAppointmentsResponse>> call = serviceCalls.getAllAppointments(sessionManager.getDOCTORID());

        call.enqueue(new Callback<List<AllAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<AllAppointmentsResponse>> call, Response<List<AllAppointmentsResponse>> response) {

                if (response.code() == 200) {
                    List<AllAppointmentsResponse> allAppointmentsResponses = response.body();
                    if (allAppointmentsResponses.size()==0){
                        showAlertDialog("No Appointments Found");
                    }else {
                        allAppointmentsAdapter = new AllAppointmentsAdapter(AllAppointmentsActivity.this, allAppointmentsResponses);
                        rview.setAdapter(allAppointmentsAdapter);
                        allAppointmentsAdapter.notifyDataSetChanged();

//                        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
//                        linearSnapHelper.attachToRecyclerView(rview);

                    }
                } else {
                    showAlertDialog("Error :"+response.code());
                }
            }

            @Override
            public void onFailure(Call<List<AllAppointmentsResponse>> call, Throwable t) {
                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    public void getlistOfConfirmedAppointments() {
        Call<List<ConfirmedAppointmentsResponse>> call = serviceCalls.getListOfConfirmedAppointments(sessionManager.getDOCTORID());
//        showDialog();
        call.enqueue(new Callback<List<ConfirmedAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<ConfirmedAppointmentsResponse>> call, Response<List<ConfirmedAppointmentsResponse>> response) {
//                closeDialog();
                if (response.code() == 200) {
                    List<ConfirmedAppointmentsResponse> confirmedAppointmentsResponseList = response.body();
                    if (confirmedAppointmentsResponseList.size()==0){
                        rview.setAdapter(null);
                        showAlertDialog("No Confirmed Appointments Found");
                    }else {
                        confirmedAppointmentsAdapter = new ConfirmedAppointmentsAdapter(AllAppointmentsActivity.this,confirmedAppointmentsResponseList);
                        rview.setAdapter(confirmedAppointmentsAdapter);
                        confirmedAppointmentsAdapter.notifyDataSetChanged();

//                        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
//                        linearSnapHelper.attachToRecyclerView(rview);

                    }
                } else {
                    showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ConfirmedAppointmentsResponse>> call, Throwable t) {
//                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }
}
