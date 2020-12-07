package com.example.planterria;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.planterria.database.addPlant;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "works", Toast.LENGTH_LONG).show();

        addPlant("aloe");

    }




}