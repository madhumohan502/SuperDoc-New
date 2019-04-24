package com.example.ihubtechnologies.superdocnew.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.activities.AllAppointmentsActivity;
import com.example.ihubtechnologies.superdocnew.activities.DoctorSessionActivity;
import com.example.ihubtechnologies.superdocnew.holders.DoctorSessionHolder;
import com.example.ihubtechnologies.superdocnew.pojos.response.DoctorSessionResponse;

import java.util.List;

public class DoctorSessionAdapter extends RecyclerView.Adapter<DoctorSessionHolder> {
    DoctorSessionActivity doctorSessionActivity;
    List<DoctorSessionResponse> doctorSessionResponses;

    public DoctorSessionAdapter(DoctorSessionActivity doctorSessionActivity, List<DoctorSessionResponse> doctorSessionResponses) {
        this.doctorSessionActivity = doctorSessionActivity;
        this.doctorSessionResponses = doctorSessionResponses;
    }

    @NonNull
    @Override
    public DoctorSessionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(doctorSessionActivity);
        View view = inflater.inflate(R.layout.doctor_session_itemview, viewGroup, false);
        DoctorSessionHolder holder = new DoctorSessionHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorSessionHolder holder, final int i) {
        holder.tvDate.setText(String.valueOf(doctorSessionResponses.get(i).getTodayAppointmentsCount()));
        holder.tvHospitalName.setText(doctorSessionResponses.get(i).getOrganizationName());
        holder.tvFromToTime.setText(doctorSessionResponses.get(i).getSessionTime());
        holder.tvDate.setTypeface(doctorSessionActivity.faceLight);
        holder.tvHospitalName.setTypeface(doctorSessionActivity.faceLight);
        holder.tvFromToTime.setTypeface(doctorSessionActivity.faceLight);
        ViewCompat.setTransitionName(holder.transation_layout, doctorSessionResponses.get(i).getOrganizationName());


        holder.cardview_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(doctorSessionActivity, AllAppointmentsActivity.class);
//                intent.putExtra("AppointmentsCount", String.valueOf(doctorSessionResponses.get(i).getTodayAppointmentsCount()));
//                intent.putExtra("OrganizationName", doctorSessionResponses.get(i).getOrganizationName());
//                intent.putExtra("SessionTime", doctorSessionResponses.get(i).getSessionTime());
//                doctorSessionActivity.startActivity(intent);
                doctorSessionActivity.sendData(i,holder.transation_layout,doctorSessionResponses.get(i).getTodayAppointmentsCount(),doctorSessionResponses.get(i).getOrganizationName(),doctorSessionResponses.get(i).getSessionTime());
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorSessionResponses.size();
    }

    public interface redirector{
        public void sendData(int a, LinearLayout linearLayout, int count, String orgName, String time);
    }
}
