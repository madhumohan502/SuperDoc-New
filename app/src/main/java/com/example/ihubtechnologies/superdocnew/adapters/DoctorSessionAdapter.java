package com.example.ihubtechnologies.superdocnew.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
    public void onBindViewHolder(@NonNull DoctorSessionHolder holder, int i) {
        holder.tvDate.setText(doctorSessionResponses.get(i).getTodayAppointmentsCount());
        holder.tvHospitalName.setText(doctorSessionResponses.get(i).getOrganizationName());
        holder.tvFromToTime.setText(doctorSessionResponses.get(i).getDoctorSessionId());

        holder.cardview_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(doctorSessionActivity, AllAppointmentsActivity.class);
                doctorSessionActivity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorSessionResponses.size();
    }
}
