package com.example.ihubtechnologies.superdocnew.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.adapters.AllAppointmentsAdapter;
import com.example.ihubtechnologies.superdocnew.adapters.CancelledAppointmentsAdapter;
import com.example.ihubtechnologies.superdocnew.adapters.ConfirmedAppointmentsAdapter;
import com.example.ihubtechnologies.superdocnew.adapters.NoShowAppointmentsAdapter;
import com.example.ihubtechnologies.superdocnew.pojos.response.AllAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.GetListOfCancelledAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.ConfirmedAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.GetListOfNoShowAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;

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
    public NoShowAppointmentsAdapter noShowAppointmentsAdapter;
    public CancelledAppointmentsAdapter cancelledAppointmentsAdapter;

    String AppointmentsCount, OrganizationName, SessionTime;
    int int_allAppointmentsSize, int_noshowAppointmentsSize, int_checkinAppointmentsSize, int_cancelAppointmentsSize;
    LinearLayout linearLayout;
    CardView cardView2;
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

        linearLayout = findViewById(R.id.id_layout1);
        cardView2 = findViewById(R.id.id_card_item2);

        tvAppointmentsCount.setTypeface(faceLight);
        tvHospitalName.setTypeface(faceLight);
        tvSessionTime.setTypeface(faceLight);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName =  getIntent().getExtras().getString("name");
            cardView2.setTransitionName(imageTransitionName);
            tvAppointmentsCount.setText(AppointmentsCount);
            tvHospitalName.setText(OrganizationName);
            tvSessionTime.setText(SessionTime);
            supportStartPostponedEnterTransition();
        }

        rview = findViewById(R.id.rview);
        linearLayoutManager = new LinearLayoutManager(this);
        rview.setLayoutManager(linearLayoutManager);
        rview.setHasFixedSize(true);

        getAllAppointmentsSize();
        getlistOfConfirmedAppointmentsSize();
        getlistOfNoShowAppointmentsSize();
        getlistOfCancelledAppointmentsSize();

        getAllAppointments();


        allAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                getAllAppointments();

                getlistOfCancelledAppointmentsSize();
                getlistOfNoShowAppointmentsSize();
                getlistOfConfirmedAppointmentsSize();
                getAllAppointmentsSize();
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

                getlistOfCancelledAppointmentsSize();
                getlistOfNoShowAppointmentsSize();
                getlistOfConfirmedAppointmentsSize();
                getAllAppointmentsSize();
            }
        });
        noshowAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                getlistOfNoShowAppointments();

                getlistOfCancelledAppointmentsSize();
                getlistOfNoShowAppointmentsSize();
                getlistOfConfirmedAppointmentsSize();
                getAllAppointmentsSize();
            }
        });
        cancelAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                checkinAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                noshowAppointments.setBackgroundResource(R.drawable.tv_bottom_line_light);
                cancelAppointments.setBackgroundResource(R.drawable.tv_bottom_line_dark);
                getlistOfCancelledAppointments();

                getlistOfCancelledAppointmentsSize();
                getlistOfNoShowAppointmentsSize();
                getlistOfConfirmedAppointmentsSize();
                getAllAppointmentsSize();
            }
        });

    }


    public void getAllAppointmentsSize() {
        Call<List<AllAppointmentsResponse>> call = serviceCalls.getAllAppointments(sessionManager.getDOCTORID());
//        showDialog();
        call.enqueue(new Callback<List<AllAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<AllAppointmentsResponse>> call, Response<List<AllAppointmentsResponse>> response) {
//                closeDialog();
                if (response.code() == 200) {
                    List<AllAppointmentsResponse> allAppointmentsResponses = response.body();
                    int_allAppointmentsSize = allAppointmentsResponses.size();
                    Log.d("sizeeeee", String.valueOf(int_allAppointmentsSize));
                    allAppointmentsSize.setText(String.valueOf(int_allAppointmentsSize));

                }
//                else {
//                    showAlertDialog("Error :" + response.code() + "7");
//                }
            }

            @Override
            public void onFailure(Call<List<AllAppointmentsResponse>> call, Throwable t) {
//                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    public void getAllAppointments() {
        Call<List<AllAppointmentsResponse>> call = serviceCalls.getAllAppointments(sessionManager.getDOCTORID());
//        showDialog();
        call.enqueue(new Callback<List<AllAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<AllAppointmentsResponse>> call, Response<List<AllAppointmentsResponse>> response) {
//                closeDialog();
                if (response.code() == 200) {
                    List<AllAppointmentsResponse> allAppointmentsResponses = response.body();
                    if (allAppointmentsResponses.size()==0){
                        rview.setAdapter(null);
                        showAlertDialog("No Appointments Found");
                    }else {
                        allAppointmentsAdapter = new AllAppointmentsAdapter(AllAppointmentsActivity.this, allAppointmentsResponses);
                        rview.setAdapter(allAppointmentsAdapter);
                        allAppointmentsAdapter.notifyDataSetChanged();


//                        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
//                        linearSnapHelper.attachToRecyclerView(rview);

                    }
                } else {
                    showAlertDialog("Error :" + response.code() + "appointments");
                }
            }

            @Override
            public void onFailure(Call<List<AllAppointmentsResponse>> call, Throwable t) {
//                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }


    public void getlistOfConfirmedAppointmentsSize() {
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
                }
//                else {
//                    showAlertDialog("Error :" + response.code() + "5");
//                }
            }

            @Override
            public void onFailure(Call<List<ConfirmedAppointmentsResponse>> call, Throwable t) {
//                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void getlistOfConfirmedAppointments() {
        Call<List<ConfirmedAppointmentsResponse>> call = serviceCalls.getListOfConfirmedAppointments(sessionManager.getDOCTORID());
        showDialog();
        call.enqueue(new Callback<List<ConfirmedAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<ConfirmedAppointmentsResponse>> call, Response<List<ConfirmedAppointmentsResponse>> response) {
                closeDialog();
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
                    showAlertDialog("Error :" + response.code() + "confirmed");
                }
            }

            @Override
            public void onFailure(Call<List<ConfirmedAppointmentsResponse>> call, Throwable t) {
                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    public void getlistOfCancelledAppointmentsSize() {
        Call<List<GetListOfCancelledAppointmentsResponse>> call = serviceCalls.getListOfCancelledAppointments(sessionManager.getDOCTORID());
        call.enqueue(new Callback<List<GetListOfCancelledAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<GetListOfCancelledAppointmentsResponse>> call, Response<List<GetListOfCancelledAppointmentsResponse>> response) {
                if (response.code() == 200) {
                    List<GetListOfCancelledAppointmentsResponse> cancelledAppointmentsResponseList = response.body();
                    int_cancelAppointmentsSize = cancelledAppointmentsResponseList.size();
                    cancelAppointmentsSize.setText(String.valueOf(int_cancelAppointmentsSize));
                }
//                else {
//                    showAlertDialog("Error :" + response.code() + "1");
//                }
            }

            @Override
            public void onFailure(Call<List<GetListOfCancelledAppointmentsResponse>> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void getlistOfCancelledAppointments() {
        Call<List<GetListOfCancelledAppointmentsResponse>> call = serviceCalls.getListOfCancelledAppointments(sessionManager.getDOCTORID());
        showDialog();
        call.enqueue(new Callback<List<GetListOfCancelledAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<GetListOfCancelledAppointmentsResponse>> call, Response<List<GetListOfCancelledAppointmentsResponse>> response) {
                closeDialog();
                if (response.code() == 200) {
                    List<GetListOfCancelledAppointmentsResponse> cancelledAppointmentsResponseList = response.body();
                    if (cancelledAppointmentsResponseList.size() == 0) {
                        rview.setAdapter(null);
                        showAlertDialog("No Cancelled Appointments Found");
                    } else {
                        cancelledAppointmentsAdapter = new CancelledAppointmentsAdapter(AllAppointmentsActivity.this, cancelledAppointmentsResponseList);
                        rview.setAdapter(cancelledAppointmentsAdapter);
                        cancelledAppointmentsAdapter.notifyDataSetChanged();
                    }
                } else {
                    showAlertDialog("Error :" + response.code() + "cancel");
                }
            }

            @Override
            public void onFailure(Call<List<GetListOfCancelledAppointmentsResponse>> call, Throwable t) {
                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    public void getlistOfNoShowAppointmentsSize() {
        Call<List<GetListOfNoShowAppointmentsResponse>> call = serviceCalls.getListOfNoShowAppointments(sessionManager.getDOCTORID());
        call.enqueue(new Callback<List<GetListOfNoShowAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<GetListOfNoShowAppointmentsResponse>> call, Response<List<GetListOfNoShowAppointmentsResponse>> response) {
                if (response.code() == 200) {
                    List<GetListOfNoShowAppointmentsResponse> getListOfNoShowAppointmentsResponseList = response.body();
                    int_noshowAppointmentsSize = getListOfNoShowAppointmentsResponseList.size();
                    noshowAppointmentsSize.setText(String.valueOf(int_noshowAppointmentsSize));
                }
//                else {
//                    showAlertDialog("Error :" + response.code() + "no_show_size");
//                }
            }

            @Override
            public void onFailure(Call<List<GetListOfNoShowAppointmentsResponse>> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });

    }

    private void getlistOfNoShowAppointments() {
        Call<List<GetListOfNoShowAppointmentsResponse>> call = serviceCalls.getListOfNoShowAppointments(sessionManager.getDOCTORID());
        showDialog();
        call.enqueue(new Callback<List<GetListOfNoShowAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<GetListOfNoShowAppointmentsResponse>> call, Response<List<GetListOfNoShowAppointmentsResponse>> response) {
                closeDialog();
                if (response.code() == 200) {
                    List<GetListOfNoShowAppointmentsResponse> noShowAppointmentsResponseList = response.body();
                    if (noShowAppointmentsResponseList.size() == 0) {
                        rview.setAdapter(null);
                        showAlertDialog("No no-show Appointments Found");
                    } else {
                        noShowAppointmentsAdapter = new NoShowAppointmentsAdapter(AllAppointmentsActivity.this, noShowAppointmentsResponseList);
                        rview.setAdapter(noShowAppointmentsAdapter);
                        noShowAppointmentsAdapter.notifyDataSetChanged();
                    }
                } else {
                    showAlertDialog("Error :" + response.code() + "no-show");
                }
            }

            @Override
            public void onFailure(Call<List<GetListOfNoShowAppointmentsResponse>> call, Throwable t) {
                closeDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllAppointmentsSize();
        getlistOfConfirmedAppointmentsSize();
        getlistOfNoShowAppointmentsSize();
        getlistOfCancelledAppointmentsSize();
    }
}
