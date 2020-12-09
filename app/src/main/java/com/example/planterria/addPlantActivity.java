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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class addPlantActivity extends AppCompatActivity {
EditText plantName;
EditText plantLocation;
EditText plantIdentifer;
TextView validTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        plantName = (EditText)findViewById(R.id.addPlantNameInput);
        plantLocation = (EditText)findViewById(R.id.addPlantLocationInput);
        plantIdentifer = (EditText)findViewById(R.id.addPlantIdentifierInput);
        validTV = (TextView)findViewById(R.id.validTV);


        configureBackButton();
        configureAddButton(plantName, plantLocation, plantIdentifer);
    }


    private void configureBackButton(){
        Button button = findViewById(R.id.backButton1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void configureAddButton(EditText plantName,
            EditText plantLocation,
            EditText plantIdentifer){
        Button button = findViewById(R.id.addPlantToGardenButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getPlant(plantName, plantLocation, plantIdentifer, validTV);

                Toast.makeText(addPlantActivity.this, "yike", Toast.LENGTH_LONG).show();
            }
        });
    }


    public static void getPlant(EditText plantName,
                                EditText plantLocation,
                                EditText plantIdentifer,
                                TextView validTV) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query myRef = database.getInstance().getReference("plants")
                .orderByChild("name")
                .equalTo(plantName.getText().toString());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        plant p = snapshot.getValue(plant.class);
                        Log.i("name:", p.toString());
                        Houseplant hp = new Houseplant(p,
                                plantLocation.getText().toString(),
                                plantIdentifer.getText().toString());
                        addHousePlant(hp);
                        validTV.setText("ADDED");

                    }

                }
                else{
                    validTV.setText("Not in Database");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Wrong:", "Could not find value");
                validTV.setText("Not Found");

            }
        });
    }

    public static void addHousePlant(Houseplant houseplant){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("HomeGarden");
        myRef.push().setValue(houseplant);
    }
}