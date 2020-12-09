package com.example.planterria;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.List;

public class viewHouseGarden extends AppCompatActivity {

    ListView plantList;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_house_garden);

        plantList = (ListView) findViewById(R.id.listOfHousePlants);
        textView3 = (TextView)findViewById(R.id.textView3);

        ArrayList<Houseplant> listofhouseplants = new ArrayList<>();
        ArrayAdapter<Houseplant> houseplantArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, listofhouseplants
        );
        plantList.setAdapter(houseplantArrayAdapter);
        getHousePlants(plantList, listofhouseplants, houseplantArrayAdapter, textView3);


    }

    public static void getHousePlants(ListView plantList, ArrayList<Houseplant> listofhouseplants,
                                      ArrayAdapter<Houseplant> houseplantArrayAdapter,
                                      TextView textView3) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query myRef = database.getInstance().getReference("HomeGarden");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<Houseplant> listofhouseplants = new ArrayList<>();
                    String output = "";
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Houseplant currentHouseplant = snapshot.getValue(Houseplant.class);
                        listofhouseplants.add(currentHouseplant);
                        output = output + currentHouseplant.toString() + "\n\n";

                    }

                    textView3.setText(output);



                    houseplantArrayAdapter.notifyDataSetChanged();

                } else {
                    textView3.setText("Not");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Wrong:", "Could not find value");
               // nameTV.setText("Not Found");
                textView3.setText("Not");

            }
        });
    }




}