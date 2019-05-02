package com.example.ihubtechnologies.superdocnew.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ClipData;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.example.ihubtechnologies.superdocnew.R;
import com.example.ihubtechnologies.superdocnew.activities.AllAppointmentsActivity;
import com.example.ihubtechnologies.superdocnew.holders.AllAppointmentsHolder;
import com.example.ihubtechnologies.superdocnew.pojos.request.CloseConsultantRequest;
import com.example.ihubtechnologies.superdocnew.pojos.request.StartConsultantRequest;
import com.example.ihubtechnologies.superdocnew.pojos.response.AllAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.CancelAppointmentResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.CloseConsultantResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.ConfirmedAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.NoShowAppointmentsResponse;
import com.example.ihubtechnologies.superdocnew.pojos.response.StartConsultantResponse;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.FrameMetrics.ANIMATION_DURATION;

public class AllAppointmentsAdapter extends RecyclerSwipeAdapter<AllAppointmentsHolder> {
    AllAppointmentsActivity allAppointmentsActivity;
    List<AllAppointmentsResponse> allAppointmentsResponses;
    int appid, currentAppID;
    int adapterPostion;
    boolean undo;

    ////to open one recycler-view item at a time
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
        holder.tvPatientName.setText(allAppointmentsResponses.get(i).getPatientName() + "/" + allAppointmentsResponses.get(i).getApptID());
        holder.tvApptStatus.setText(allAppointmentsResponses.get(i).getApptStatus());
//        holder.tvTime.setTypeface(allAppointmentsActivity.faceLight);
//        holder.tvPatientName.setTypeface(allAppointmentsActivity.faceLight);
//        holder.tvApptStatus.setTypeface(allAppointmentsActivity.faceLight);


        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.left));
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.right_to_left));
//to open one recycler-view item at a time
        mItemManger.bindView(holder.itemView, i);
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

                //  appid = allAppointmentsResponses.get(i).getApptID();
                if (layout.getOpenStatus() == SwipeLayout.Status.Open) {
//                    int elapsedMillis = (int) (SystemClock.elapsedRealtime() - holder.chronometer.getBase());
//                    String elapsedTime = holder.chronometer.getText().toString();
//                    if (holder.running == true && elapsedMillis > 3000) {
                    mItemManger.closeAllExcept(layout);
                    //allAppointmentsActivity.sessionManager.setAPPID("0");

                }
//                }
            }

            @Override
            public void onOpen(SwipeLayout layout) {
//                allAppointmentsActivity.sessionManager.logout();
//                currentAppID = 0;
                appid = allAppointmentsResponses.get(i).getApptID();
                currentAppID = appid;

//                allAppointmentsActivity.sessionManager.setAPPID(String.valueOf(appid));
                Log.d("appid-open", String.valueOf(appid));
                //starting consultaion
                if (layout.getDragEdge() == SwipeLayout.DragEdge.Left) {
//starting timer
                    startChronometer(holder);
//swaping item to top
                    adapterPostion = holder.getAdapterPosition();
                    Collections.swap(allAppointmentsResponses, holder.getAdapterPosition(), 0);
                    allAppointmentsActivity.allAppointmentsAdapter.notifyItemMoved(holder.getAdapterPosition(), 0);
                    allAppointmentsActivity.rview.scrollToPosition(0);
//                    allAppointmentsActivity.allAppointmentsAdapter.notifyItemChanged(holder.getAdapterPosition());
//                    allAppointmentsActivity.allAppointmentsAdapter.notifyItemChanged(0);
                    //allAppointmentsActivity.allAppointmentsAdapter.notifyDataSetChanged();

                    Log.d("adapterposition", String.valueOf(adapterPostion));

                    layout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (layout.getOpenStatus() == SwipeLayout.Status.Open) {
                                if (layout.getDragEdge() == SwipeLayout.DragEdge.Left) {
                                    if (holder.layout_undo.getVisibility() == View.VISIBLE) {
                                        try {

                                            startConsultation(holder, appid);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    holder.swipeLayout2.setRightSwipeEnabled(false);
                                    holder.swipeLayout2.setShowMode(SwipeLayout.ShowMode.LayDown);
                                    holder.swipeLayout2.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.left_to_right));
                                    holder.swipeLayout2.addSwipeListener(new SwipeLayout.SwipeListener() {
                                        @Override
                                        public void onStartOpen(SwipeLayout layout) {

                                        }

                                        //62319809894
                                        @Override
                                        public void onOpen(SwipeLayout layout) {
                                            appid = allAppointmentsResponses.get(i).getApptID();
                                            stopChronometer(holder, appid);
                                            holder.swipeLayout2.close();
                                            holder.swipeLayout.close();
                                            Log.d("appid-sublayout", String.valueOf(appid));

                                        }

                                        @Override
                                        public void onStartClose(SwipeLayout layout) {

                                        }

                                        @Override
                                        public void onClose(SwipeLayout layout) {
//
                                        }

                                        @Override
                                        public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

                                        }

                                        @Override
                                        public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

                                        }
                                    });


                                }
                            }
                        }
                    }, 3000);


                }

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {
              if (undo){
                  undo = true;
                  allAppointmentsActivity.showToast("undo clicked");
              }else {
                  undo = false;
                  holder.swipeLayout.setSwipeEnabled(true);
                  stopChronometer(holder, currentAppID);
                  allAppointmentsActivity.showToast("undo not clicked");
                  Log.d("appid-close", String.valueOf(currentAppID));
              }

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

        holder.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appid = allAppointmentsResponses.get(i).getApptID();
                stopChronometer(holder, appid);
                holder.swipeLayout2.close();
                holder.swipeLayout.close();
                holder.swipeLayout.setSwipeEnabled(true);
//                checkOut(holder, appid);
            }
        });

        holder.undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undo = true;
                holder.swipeLayout.close();

                Collections.swap(allAppointmentsResponses, 0, adapterPostion);
                allAppointmentsActivity.allAppointmentsAdapter.notifyItemMoved(0, adapterPostion);
//                allAppointmentsActivity.allAppointmentsAdapter.notifyDataSetChanged();

                appid = allAppointmentsResponses.get(i).getApptID();

                resetChronometer(holder, appid);
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
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appid = allAppointmentsResponses.get(i).getApptID();
                cancelAppointment(holder, appid);
            }
        });
        holder.noshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appid = allAppointmentsResponses.get(i).getApptID();
                boolean isShow = true;
                noShowAppointment(appid, isShow, holder);
            }
        });

    }


    private void checkOut(AllAppointmentsHolder holder, int appid) {

    }

    private void noShowAppointment(int appid, boolean isShow, AllAppointmentsHolder holder) {
        Call<NoShowAppointmentsResponse> call = allAppointmentsActivity.serviceCalls.noShowAppointment(appid, isShow);
        call.enqueue(new Callback<NoShowAppointmentsResponse>() {
            @Override
            public void onResponse(Call<NoShowAppointmentsResponse> call, Response<NoShowAppointmentsResponse> response) {
                if (response.code() == 200) {
                    allAppointmentsActivity.showAlertDialog(response.body().getMsg());
                    holder.swipeLayout.close();

                    allAppointmentsActivity.getlistOfCancelledAppointmentsSize();
                    allAppointmentsActivity.getlistOfNoShowAppointmentsSize();
                    allAppointmentsActivity.getlistOfConfirmedAppointmentsSize();
                    allAppointmentsActivity.getAllAppointmentsSize();
                } else {
                    allAppointmentsActivity.showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<NoShowAppointmentsResponse> call, Throwable t) {
                allAppointmentsActivity.showAlertDialog(t.getMessage());
            }
        });
    }

    private void cancelAppointment(AllAppointmentsHolder holder, int appid) {

        Call<CancelAppointmentResponse> call = allAppointmentsActivity.serviceCalls.cancelAppointment(appid);
        call.enqueue(new Callback<CancelAppointmentResponse>() {
            @Override
            public void onResponse(Call<CancelAppointmentResponse> call, Response<CancelAppointmentResponse> response) {
                if (response.code() == 200) {
                    allAppointmentsActivity.showAlertDialog(response.body().getMsg());
                    holder.swipeLayout.close();

                    allAppointmentsActivity.getlistOfCancelledAppointmentsSize();
                    allAppointmentsActivity.getlistOfNoShowAppointmentsSize();
                    allAppointmentsActivity.getlistOfConfirmedAppointmentsSize();
                    allAppointmentsActivity.getAllAppointmentsSize();
                } else {
                    allAppointmentsActivity.showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<CancelAppointmentResponse> call, Throwable t) {
                allAppointmentsActivity.showAlertDialog(t.getMessage());
            }
        });
    }

    private void stopChronometer(AllAppointmentsHolder holder, int appid) {
        if (holder.running) {
            //allAppointmentsActivity.sessionManager.logout();
//            currentAppID = 0;
            holder.chronometer.stop();
            holder.pauseOffSet = SystemClock.elapsedRealtime() - holder.chronometer.getBase();
            holder.running = false;
            allAppointmentsActivity.showToast("chronometer-stopped" + "\n" + "consultation closed successfully");
            int elapsedMillis = (int) (SystemClock.elapsedRealtime() - holder.chronometer.getBase());
            String elapsedTime = holder.chronometer.getText().toString();
            Log.d("elapsedMillis1", String.valueOf(elapsedMillis));
            Log.d("elapsedMillis2", holder.chronometer.getText().toString());
            closeConsultation(holder, appid, elapsedMillis, elapsedTime);
        }
    }

    private void resetChronometer(AllAppointmentsHolder holder, int appid) {

        holder.chronometer.setBase(SystemClock.elapsedRealtime());
        holder.pauseOffSet = 0;
        allAppointmentsActivity.showToast("chronometer-reset");

    }

    private void startChronometer(AllAppointmentsHolder holder) {
        if (!holder.running) {
            holder.chronometer.setBase(SystemClock.elapsedRealtime() - holder.pauseOffSet);
            holder.chronometer.start();
            holder.running = true;

        }
    }


    private void startConsultation(AllAppointmentsHolder holder, int appid) {
        /**
         * appId : 120
         * startConsultant : 1
         */
        StartConsultantRequest startConsultantRequest = new StartConsultantRequest(appid);
        Log.d("startConsultantRequest", startConsultantRequest.toString());
        Call<StartConsultantResponse> call = allAppointmentsActivity.serviceCalls.doStartConsultant(startConsultantRequest);
//        allAppointmentsActivity.showDialog();
        call.enqueue(new Callback<StartConsultantResponse>() {
            @Override
            public void onResponse(Call<StartConsultantResponse> call, Response<StartConsultantResponse> response) {
//                allAppointmentsActivity.closeDialog();
                if (response.code() == 200) {
//                    currentAppID = appid;
//                    Log.d("appid-open", String.valueOf(appid));
                    StartConsultantResponse startConsultantResponse = response.body();
                    //allAppointmentsActivity.showAlertDialog(startConsultantResponse.getMsg());
                    allAppointmentsActivity.showToast(startConsultantResponse.getMsg());


// slide-up animation
                    Animation undoAnimation = AnimationUtils.loadAnimation(allAppointmentsActivity, R.anim.undo);

                    if (holder.layout_timer.getVisibility() == View.GONE) {
                        holder.layout_timer.setVisibility(View.VISIBLE);
                        holder.layout_undo.setVisibility(View.GONE);
                        holder.layout_timer.startAnimation(undoAnimation);
                    }

                      holder.swipeLayout.setSwipeEnabled(false);

                } else {
                    allAppointmentsActivity.showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<StartConsultantResponse> call, Throwable t) {
//                allAppointmentsActivity.closeDialog();
                allAppointmentsActivity.showAlertDialog(t.getMessage());
            }
        });
    }

    private void closeConsultation(AllAppointmentsHolder holder, int appid, int elapsedMillis, String elapsedTime) {
        /**
         * appId : 120
         * startConsultant : 1
         */
        CloseConsultantRequest closeConsultantRequest = new CloseConsultantRequest(appid);
        Log.d("startConsultantRequest", closeConsultantRequest.toString());
        Call<CloseConsultantResponse> call = allAppointmentsActivity.serviceCalls.doCloseConsultant(closeConsultantRequest);
        allAppointmentsActivity.showDialog();
        call.enqueue(new Callback<CloseConsultantResponse>() {
            @Override
            public void onResponse(Call<CloseConsultantResponse> call, Response<CloseConsultantResponse> response) {
                allAppointmentsActivity.closeDialog();
                if (response.code() == 200) {
                    CloseConsultantResponse closeConsultantResponse = response.body();
                    holder.swipeLayout.close();
                    allAppointmentsActivity.showToast(closeConsultantResponse.getMsg() + "\n" + "time :" + elapsedTime);
                    allAppointmentsActivity.getAllAppointments();

                    allAppointmentsActivity.getlistOfCancelledAppointmentsSize();
                    allAppointmentsActivity.getlistOfNoShowAppointmentsSize();
                    allAppointmentsActivity.getlistOfConfirmedAppointmentsSize();
                    allAppointmentsActivity.getAllAppointmentsSize();

                } else {
                    allAppointmentsActivity.showAlertDialog("Error :" + response.code());
                }
            }

            @Override
            public void onFailure(Call<CloseConsultantResponse> call, Throwable t) {
                allAppointmentsActivity.closeDialog();
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
