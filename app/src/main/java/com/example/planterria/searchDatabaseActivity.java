package com.example.planterria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class searchDatabaseActivity extends AppCompatActivity {
EditText nameInput;
TextView species;
TextView lightTV;
TextView waterTV;
plant foundplant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdatabase);
        configureBackButton();


        nameInput = (EditText)findViewById(R.id.nameInput);
        species = (TextView)findViewById(R.id.plantName);
        lightTV = (TextView)findViewById(R.id.plantLight);
        waterTV = (TextView)findViewById(R.id.waterInfo);





        configureSubmitButton(species, lightTV, waterTV);

    }


    private void configureBackButton(){
        Button button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void configureSubmitButton(TextView nameTV, TextView lightTV, TextView waterTV){


        Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ok = nameInput.getText().toString();
                getPlant(ok,  nameTV,  lightTV,  waterTV);

                Toast.makeText(searchDatabaseActivity.this, ok, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void getPlant(String name, TextView nameTV, TextView lightTV, TextView waterTV){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query myRef = database.getInstance().getReference("plants")
                .orderByChild("name")
                .equalTo(name);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        plant p = snapshot.getValue(plant.class);
                        Log.i("name:", p.toString());
                        nameTV.setText(p.getName());
                        lightTV.setText(p.getLightLevel());
                        waterTV.setText(p.getWaterAmount());
                    }

                }
                else{
                    nameTV.setText("Not in Database");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Wrong:", "Could not find value");
                nameTV.setText("Not Found");

            }
        });
    }
}