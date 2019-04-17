package com.example.ihubtechnologies.superdocnew.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.pojos.request.CloseConsultantRequest;
import com.example.ihubtechnologies.superdocnew.pojos.request.StartConsultantRequest;
import com.example.ihubtechnologies.superdocnew.pojos.response.AllAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.CloseConsultantResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.ClosedAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.ConfirmedAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.StartConsultantResponse;
import com.example.ihubtechnologies.superdocnew.utils.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends BaseActivity {

    private String doctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    private void listOfAllAppointments() {
        Call<List<AllAppointmentsResponse>> call = serviceCalls.getListOfAllAppointments(doctorId);
        call.enqueue(new Callback<List<AllAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<AllAppointmentsResponse>> call, Response<List<AllAppointmentsResponse>> response) {
                if (response.code() == 200) {

                } else {
                    showAlertDialog("failed");
                }
            }

            @Override
            public void onFailure(Call<List<AllAppointmentsResponse>> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void startConsultant() {
        /**
         * appId : 120
         * startConsultant : 1
         */
        StartConsultantRequest startConsultantRequest = new StartConsultantRequest(0, 0);
        Call<StartConsultantResponse> call = serviceCalls.doStartConsultant(startConsultantRequest);
        call.enqueue(new Callback<StartConsultantResponse>() {
            @Override
            public void onResponse(Call<StartConsultantResponse> call, Response<StartConsultantResponse> response) {
                if (response.code() == 200) {

                } else {
                    showAlertDialog("failed");
                }
            }

            @Override
            public void onFailure(Call<StartConsultantResponse> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void listOfConfirmedAppointments() {
        Call<List<ConfirmedAppointmentsResponse>> call = serviceCalls.getListOfConfirmedAppointments(doctorId);
        call.enqueue(new Callback<List<ConfirmedAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<ConfirmedAppointmentsResponse>> call, Response<List<ConfirmedAppointmentsResponse>> response) {
                if (response.code() == 200) {

                } else {
                    showAlertDialog("failed");
                }
            }

            @Override
            public void onFailure(Call<List<ConfirmedAppointmentsResponse>> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void closeConsultant() {
        CloseConsultantRequest closeConsultantRequest = new CloseConsultantRequest(0, 0);
        Call<CloseConsultantResponse> call = serviceCalls.doCloseConsultant(closeConsultantRequest);
        call.enqueue(new Callback<CloseConsultantResponse>() {
            @Override
            public void onResponse(Call<CloseConsultantResponse> call, Response<CloseConsultantResponse> response) {
                if (response.code() == 200) {

                } else {
                    showAlertDialog("failed");
                }
            }

            @Override
            public void onFailure(Call<CloseConsultantResponse> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void listOfAllClosedAppointments() {
        Call<List<ClosedAppointmentsResponse>> call = serviceCalls.getAllClosedAppointments(doctorId);
        call.enqueue(new Callback<List<ClosedAppointmentsResponse>>() {
            @Override
            public void onResponse(Call<List<ClosedAppointmentsResponse>> call, Response<List<ClosedAppointmentsResponse>> response) {
                if (response.code() == 200) {

                } else {
                    showAlertDialog("failed");
                }
            }

            @Override
            public void onFailure(Call<List<ClosedAppointmentsResponse>> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });
    }
}
