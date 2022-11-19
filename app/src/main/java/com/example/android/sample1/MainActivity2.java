package com.example.android.sample1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    TextView rname, rfname, rmname, rgender, rcontact, numberOfPerson;
    DatabaseReference databaseReference;
    Button b;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rname = findViewById(R.id.yourName);
        rfname = findViewById(R.id.yourFathersname);
        rmname = findViewById(R.id.yourMothersname);
        rgender = findViewById(R.id.yourGender);
        rcontact = findViewById(R.id.yourContact);
        b = findViewById(R.id.show);
        numberOfPerson = findViewById(R.id.person_number);
        count = 1;

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("show","start show");
                String count1 = Integer.toString(count);
//                databaseReference = FirebaseDatabase.getInstance().getReference().child("Details").child(count1);
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Details");

                Log.d("show","start show after database");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshotHead) {

                        long a  = snapshotHead.getChildrenCount();
                        Log.d("number",String.valueOf(a));
                        if(count<=(int)a){
                            DataSnapshot snapshot = snapshotHead.child(count1);
                            Log.d("number",String.valueOf(a));
                            numberOfPerson.setText(count1);
                            String retrivedName = snapshot.child("name").getValue().toString();
                            String retrivedFName = snapshot.child("fathersName").getValue().toString();
                            String retrivedMName = snapshot.child("mothersName").getValue().toString();
                            Log.d("show","before gendre retrive"+retrivedMName);
                            String retrivedGender = snapshot.child("gender").getValue().toString();
                            Log.d("show","after gender retrive"+retrivedGender);
                            String retrivedContact = snapshot.child("contactNumber").getValue().toString();

//                            Log.d("show","starting set text");

                            rname.setText(retrivedName);
                            rfname.setText(retrivedFName);
                            rmname.setText(retrivedMName);
                            rgender.setText(retrivedGender);
                            rcontact.setText(retrivedContact);
                            numberOfPerson.setText("User No. "+count);
                        }
                        else{
                            Toast.makeText(MainActivity2.this, "No more data on data base", Toast.LENGTH_SHORT).show();
                        }

                            count++;

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity2.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });



    }
}