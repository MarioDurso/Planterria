package com.example.planterria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewHouseGarden extends AppCompatActivity {

    ListView plantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_house_garden);

        plantList = (ListView) findViewById(R.id.listOfHousePlants);



        getHousePlants(plantList, this.getApplicationContext());




    }

    public static void getHousePlants(ListView plantList,
                                      Context currentContext) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query myRef = database.getInstance().getReference("HomeGarden");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<Houseplant> listofhouseplants = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Houseplant currentHouseplant = snapshot.getValue(Houseplant.class);
                        listofhouseplants.add(currentHouseplant);

                    }
                    
                    Log.i("houseplantlist", listofhouseplants.toString());
                    ArrayAdapter<Houseplant> houseplantAdapter = new ArrayAdapter<Houseplant>(currentContext, android.R.layout.simple_list_item_1, listofhouseplants);
                    plantList.setAdapter(houseplantAdapter);



                } else {
                    ArrayList<String> noPlantStringList = new ArrayList<String>();
                    noPlantStringList.add("No Plants in Garden");
                    noPlantStringList.add("You can add plants from homepage");
                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(currentContext, android.R.layout.simple_list_item_1, noPlantStringList);
                    plantList.setAdapter(stringArrayAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Wrong:", "Could not find value");
               // nameTV.setText("Not Found");
                ArrayList<String> noPlantStringList = new ArrayList<String>();
                noPlantStringList.add("No Plants in Garden");
                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(currentContext, android.R.layout.simple_list_item_1, noPlantStringList);
                plantList.setAdapter(stringArrayAdapter);

            }
        });
    }




}