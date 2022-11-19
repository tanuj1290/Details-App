package com.example.android.sample1;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3, buttonFind, imageButton;
    EditText name, fathers_name, mothers_name, dob, contact_number;
    RadioButton getGenderRadioButton;
    RadioGroup radioGroup;
    DatabaseReference reff;
    Member member;
    ImageView imageOfPerson;

    FirebaseStorage storage;
    StorageReference storageReference;

    int count = 0, id;
    String genderEntered = "Male";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Firebase connection established", Toast.LENGTH_LONG).show();

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        button1 = findViewById(R.id.submit);
        button2 = findViewById(R.id.clear);
        buttonFind = findViewById(R.id.find_person);
        name = findViewById(R.id.enter_name);
        fathers_name = findViewById(R.id.enter_fathers_name);
        mothers_name = findViewById(R.id.enter_mothers_name);
        dob = findViewById(R.id.enter_birthdate);
        contact_number = findViewById(R.id.enter_contact);
        radioGroup = findViewById(R.id.gender_radio_group);


        reff = FirebaseDatabase.getInstance().getReference().child("Details");


        member = new Member();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("submit","Submit button starts");
                count++;
                id = radioGroup.getCheckedRadioButtonId();
                getGenderRadioButton = findViewById(id);
                genderEntered = getGenderRadioButton.getText().toString();
//                Log.d("submit","Submit button after gender");
//                Log.d("submit","Submit button after contact");
                member.setName(name.getText().toString().trim());
//                Log.d("submit","Submit button after name");
                member.setFathersName(fathers_name.getText().toString().trim());
//                Log.d("submit","Submit button after father name");
                member.setMothersName(mothers_name.getText().toString().trim());
//                Log.d("submit","Submit button before member gender");
                member.setGender(genderEntered);
                member.setDob(dob.getText().toString().trim());
                member.setContactNumber(contact_number.getText().toString().trim());
                Toast.makeText(MainActivity.this, "Details Submitted Successfully !!", Toast.LENGTH_SHORT).show();
//                reff.push().setValue(contact);
                String counts = Integer.toString(count);
                reff.child(counts).setValue(member);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.getText().clear();
                radioGroup.clearCheck();
                fathers_name.getText().clear();
                mothers_name.getText().clear();
                dob.getText().clear();
                contact_number.getText().clear();
            }
        });

        button3 = findViewById(R.id.details);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Let's see the details", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                Log.d("kuku","passing to activity 2");
                startActivity(intent);
//                Log.d("kuku1","passed to activity 2");
            }
        });

        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent findIntent = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(findIntent);
            }
        });


    }

}