package com.example.planterria;


import androidx.appcompat.app.AppCompatActivity;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.planterria.database.addPlant;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    int i = 1;
    public static final int HOMENOTIFICATION = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(MainActivity.this, test.toString(), Toast.LENGTH_LONG).show();
        /*addPlant(new plant("croton","bright", waterFrequency.WEEKLY));
        addPlant(new plant("orchid","bright", waterFrequency.DAILY));
        addPlant(new plant("anthurium","bright", waterFrequency.MONTHLY));
        addPlant(new plant("snake plant","bright", waterFrequency.BIWEEKLY));
        addPlant(new plant("lilly","bright", waterFrequency.DAILY));
        addPlant(new plant("majesty palm","bright", waterFrequency.WEEKLY));*/






        configureSearchDatabaseButton();
        configureAddPlantButton();
        configureViewGardenButton();
        configureNotifButton();

    }

    private void configureSearchDatabaseButton(){
        Button button = findViewById(R.id.searchDatabaseButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, searchDatabaseActivity.class));
            }
        });
    }

    private void configureAddPlantButton(){
        Button button = findViewById(R.id.addPlantButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, addPlantActivity.class));
            }
        });
    }

    private void configureViewGardenButton(){
        Button button = findViewById(R.id.viewGardenButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, viewHouseGarden.class));
            }
        });
    }

    private void configureNotifButton(){
        Button button = findViewById(R.id.notifButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startAlarm();
            }
        });
    }

    public void scheduleJob(){
        ComponentName componentName = new ComponentName(this, updateTimeWateredService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPeriodic(15*60*1000)
                .build();

        JobScheduler scheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "Job Scheduled");
        }
        else{
            Log.d(TAG, "Job Scheduling failed: ");
        }

    }

    public void cancelJob(){

    }

    private void startAlarm(){
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, this.HOMENOTIFICATION, intent, 0);

        long fiveSecondMillis = 1000*5;
        long timeBC = Calendar.getInstance().getTimeInMillis();
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                timeBC+fiveSecondMillis,
                pendingIntent);
    }




}