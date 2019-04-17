package com.example.ihubtechnologies.superdocnew.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenaretor {

    public static final String API_BASE_URL = "http://183.82.109.67:8080/doctor_appointments/rest/doctorConsult/";

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }


}


