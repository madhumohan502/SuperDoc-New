package com.example.ihubtechnologies.superdocnew.adapters;

import android.content.ClipData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.activities.AllAppointmentsActivity;
import com.example.ihubtechnologies.superdocnew.holders.AllAppointmentsHolder;
import com.example.ihubtechnologies.superdocnew.pojos.request.CloseConsultantRequest;
import com.example.ihubtechnologies.superdocnew.pojos.request.StartConsultantRequest;
import com.example.ihubtechnologies.superdocnew.pojos.response.AllAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.CloseConsultantResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.StartConsultantResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAppointmentsAdapter extends RecyclerSwipeAdapter<AllAppointmentsHolder> {
    AllAppointmentsActivity allAppointmentsActivity;
    List<AllAppointmentsResponse> allAppointmentsResponses;
    int appid;
    int startConsult;

    SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public AllAppointmentsAdapter(AllAppointmentsActivity allAppointmentsActivity, List<AllAppointmentsResponse> allAppointmentsResponses) {
        this.allAppointmentsActivity = allAppointmentsActivity;
        this.allAppointmentsResponses = allAppointmentsResponses;


    }

    @NonNull
    @Override
    public AllAppointmentsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(allAppointmentsActivity);
        View view = layoutInflater.inflate(R.layout.all_appointments_itemview, viewGroup, false);
        AllAppointmentsHolder holder = new AllAppointmentsHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AllAppointmentsHolder holder, final int i) {
        holder.tvTime.setText(allAppointmentsResponses.get(i).getApptTime());
        holder.tvPatientName.setText(allAppointmentsResponses.get(i).getPatientName());
        holder.tvApptStatus.setText(allAppointmentsResponses.get(i).getApptStatus());
//        holder.tvTime.setTypeface(allAppointmentsActivity.faceLight);
//        holder.tvPatientName.setTypeface(allAppointmentsActivity.faceLight);
//        holder.tvApptStatus.setTypeface(allAppointmentsActivity.faceLight);
        final AllAppointmentsResponse deletedItem = allAppointmentsResponses.get(holder.getAdapterPosition());
        final int deletedIndex = holder.getAdapterPosition();


        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.left_to_right_undo));
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.right_to_left));

        mItemManger.bindView(holder.itemView, i);
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
                mItemManger.closeAllExcept(layout);
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                if (layout.getDragEdge() == SwipeLayout.DragEdge.Left){
                    layout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (layout.getOpenStatus() == SwipeLayout.Status.Open){
                                if (layout.getDragEdge() == SwipeLayout.DragEdge.Left){
                                    startConsultation(holder);
                                }
                            }
                        }
                    },5000);
                }
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });

//        holder.tvStart.setTypeface(allAppointmentsActivity.faceLight);
//        holder.tvConsult.setTypeface(allAppointmentsActivity.faceLight);
//        holder.tvUndoText.setTypeface(allAppointmentsActivity.faceLight);
        holder.undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appid = allAppointmentsResponses.get(i).getApptID();
                holder.swipeLayout.close();
//                closeConsultation(holder);

            }
        });
        holder.startConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appid = allAppointmentsResponses.get(i).getApptID();
//                boolean value = allAppointmentsResponses.get(i).isStartConsultation();
//                if (value == true) {
//                    startConsult = 1;
//                } else {
//                    startConsult = 0;
//                }

               // startConsultation();
            }
        });

    }

    private void closeConsultation(AllAppointmentsHolder holder) {
        /**
         * appId : 120
         * startConsultant : 1
         */
        CloseConsultantRequest closeConsultantRequest = new CloseConsultantRequest(appid);
        Log.d("startConsultantRequest", closeConsultantRequest.toString());
        Call<CloseConsultantResponse> call = allAppointmentsActivity.serviceCalls.doCloseConsultant(closeConsultantRequest);
        call.enqueue(new Callback<CloseConsultantResponse>() {
            @Override
            public void onResponse(Call<CloseConsultantResponse> call, Response<CloseConsultantResponse> response) {
                if (response.code() == 200) {
                    CloseConsultantResponse closeConsultantResponse = response.body();
                    allAppointmentsActivity.showAlertDialog(closeConsultantResponse.getMsg());

//                    if (holder.swipeLayout.getDragEdge() == SwipeLayout.DragEdge.Left){
                       holder.swipeLayout.close();
//                    }


                } else {
                    allAppointmentsActivity.showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<CloseConsultantResponse> call, Throwable t) {
                allAppointmentsActivity.showAlertDialog(t.getMessage());
            }
        });
    }

    private void startConsultation(AllAppointmentsHolder holder) {
        /**
         * appId : 120
         * startConsultant : 1
         */
        StartConsultantRequest startConsultantRequest = new StartConsultantRequest(appid);
        Log.d("startConsultantRequest", startConsultantRequest.toString());
        Call<StartConsultantResponse> call = allAppointmentsActivity.serviceCalls.doStartConsultant(startConsultantRequest);
        call.enqueue(new Callback<StartConsultantResponse>() {
            @Override
            public void onResponse(Call<StartConsultantResponse> call, Response<StartConsultantResponse> response) {
                if (response.code() == 200) {
                    StartConsultantResponse startConsultantResponse = response.body();
                    //allAppointmentsActivity.showAlertDialog(startConsultantResponse.getMsg());
                    allAppointmentsActivity.showToast(startConsultantResponse.getMsg());

                    holder.swipeLayout.addView(holder.swipeLayout.findViewById(R.id.layout_timer));

                } else {
                    allAppointmentsActivity.showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<StartConsultantResponse> call, Throwable t) {
                allAppointmentsActivity.showAlertDialog(t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return allAppointmentsResponses.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {

        return R.id.swipe;
    }
}
