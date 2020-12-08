package com.example.planterria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.planterria.database.getPlant;

public class addplantActivity extends AppCompatActivity {
EditText nameInput;
TextView editTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplant);
        configureBackButton();


        nameInput = (EditText)findViewById(R.id.nameInput);
        editTV = (TextView)findViewById(R.id.titleTV);





        configureSubmitButton(editTV);

    }


    private void configureBackButton(){
        Button button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void configureSubmitButton(TextView tv){


        Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ok = nameInput.getText().toString();
                getPlant(ok, tv);

                Toast.makeText(addplantActivity.this, ok, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void getPlant(String name, TextView value){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("plants").child(name);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    Log.i("name:", name);

                    value.setText(name);
                }
                else{
                    value.setText("Not in Database");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Wrong:", "Could not find value");
                value.setText("Not Found");

            }
        });
    }
}