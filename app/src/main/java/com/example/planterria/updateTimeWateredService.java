package com.example.planterria;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class updateTimeWateredService extends JobService {
    public static final String TAG = "updateTimeWatered";
    private boolean jobCancelled = false;


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: ");
        doBackgroudWork(params);

        return true;
    }

    private void doBackgroudWork(JobParameters params){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: ");
                if(jobCancelled){
                    return;
                }
                incrementTimeSinceWatered();
                jobFinished(params, false);

            }
        }).start();
    }

    private void incrementTimeSinceWatered(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getInstance().getReference("HomeGarden");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    int currentValue = ds.child("hoursSinceLastWater").getValue(int.class);
                    ds.child("hoursSinceLastWater").getRef().setValue(currentValue +1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addListenerForSingleValueEvent(eventListener);
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;

        return true;
    }
}
