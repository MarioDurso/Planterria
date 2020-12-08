package com.example.planterria;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.planterria.database.addPlant;
import static com.example.planterria.database.getPlant;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(MainActivity.this, "works", Toast.LENGTH_LONG).show();
       // addPlant("rose");
        //ArrayList<String> test = getPlant("");

        //Toast.makeText(MainActivity.this, test.toString(), Toast.LENGTH_LONG).show();
        addPlant(new plant("tulip","bright", "daily"));

        configureAddPlantButton();

    }

    private void configureAddPlantButton(){
        Button button = findViewById(R.id.addPlant);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, addplantActivity.class));
            }
        });
    }




}