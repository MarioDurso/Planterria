package com.example.planterria;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class database {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String name;

    public database(){

    }

    public void output(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Plants");
        myRef.setValue("Hello, World");
        Log.i("MyApp","I am here");
    }

    public static void addPlant(plant plant){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("plants");
        myRef.push().setValue(plant);
    }

    public static ArrayList<String> getPlant(String name){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("plants").child("rose");

        ArrayList<String> plantList = new ArrayList<>();
        plantList.add("hello");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    plantList.clear();
                    // Get Post object and use the values to update the UI
                    String name = dataSnapshot.child("name").getValue().toString();
                    plantList.add("test");
                    Log.i("name:", name);
                }
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w("loadPost:onCancelled", databaseError.toException());
                plantList.add("Not in Database");
                // ...
            }
        });
        return plantList;
    }

    public static void searchDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("plants");
    }


}


