package com.example.ihubtechnologies.superdocnew.network;


import com.example.ihubtechnologies.superdocnew.pojos.request.CloseConsultantRequest;
import com.example.ihubtechnologies.superdocnew.pojos.request.OtpVerificationRequest;
import com.example.ihubtechnologies.superdocnew.pojos.request.StartConsultantRequest;
import com.example.ihubtechnologies.superdocnew.pojos.response.AllAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.CloseConsultantResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.ClosedAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.ConfirmedAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.DoctorSessionResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.LoginResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.OtpVerificationResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.StartConsultantResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceCalls {


    @GET("loginWithMobile")
    Call<LoginResponse> doLogin(@Query("mobilenumber") String s);

    @POST("otpValidation")
    Call<OtpVerificationResponse> doVerifyOTP(@Body OtpVerificationRequest otpVerificationRequest);

    @GET("listOfSessions")
    Call<List<DoctorSessionResponse>> getDoctorSessions(@Query("doctorId") String doctorid);

    @GET("listOfAllAppointments")
    Call<List<AllAppointmentsResponse>> getListOfAllAppointments(@Query("doctorId") String doctorId);

    @POST("startConsultant")
    Call<StartConsultantResponse> doStartConsultant(@Body StartConsultantRequest startConsultantRequest);

    @GET("listOfAllConfirmedAppointments")
    Call<List<ConfirmedAppointmentsResponse>> getListOfConfirmedAppointments(@Query("doctorId") String doctorId);





    @POST("startConsultant")
    Call<CloseConsultantResponse> doCloseConsultant(@Body CloseConsultantRequest closeConsultantRequest);





    @GET("listOfAllClosedAppointments")
    Call<List<ClosedAppointmentsResponse>> getAllClosedAppointments(@Query("doctorId") String doctorId);
}
