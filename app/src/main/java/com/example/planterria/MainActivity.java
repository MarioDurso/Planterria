package com.example.planterria;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(MainActivity.this, "works", Toast.LENGTH_LONG).show();
       // addPlant("rose");
        //ArrayList<String> test = getPlant("");

        //Toast.makeText(MainActivity.this, test.toString(), Toast.LENGTH_LONG).show();
        //addPlant(new plant("tulip","bright", "daily"));

        configureSearchDatabaseButton();
        configureAddPlantButton();
        configureViewGardenButton();

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




}