package com.example.ihubtechnologies.superdocnew.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ihubtechnologies.superdocnew.R;

public class DoctorSessionHolder extends RecyclerView.ViewHolder {
    public TextView tvDate,tvHospitalName,tvFromToTime;
    public LinearLayout cardview_item;
    public DoctorSessionHolder(@NonNull View itemView) {
        super(itemView);
        tvDate = itemView.findViewById(R.id.tv_date);
        tvHospitalName = itemView.findViewById(R.id.tv_hospital_name);
        tvFromToTime = itemView.findViewById(R.id.tv_fromtime_totime);
        cardview_item = itemView.findViewById(R.id.cardview_item);
    }
}
