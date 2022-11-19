package com.example.android.sample1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    Button findDetailByButton;
    EditText e;
    TextView fa, mo, ge, cn;
    long number;
    int num;
    String value, nameEnteredToSearch;
    DatabaseReference databaseFind1, databaseFind2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        findDetailByButton = findViewById(R.id.findFromDatabase);
        fa = findViewById(R.id.showYourFathersname);
        mo = findViewById(R.id.showYourMothersname);
        ge = findViewById(R.id.showYourGender);
        cn = findViewById(R.id.showYourContact);
        e = findViewById(R.id.enter_name_to_find);

        findDetailByButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameEnteredToSearch = e.getText().toString();

                databaseFind1 = FirebaseDatabase.getInstance().getReference().child("Details");
                Toast.makeText(MainActivity3.this, "Showing the detials", Toast.LENGTH_SHORT).show();
                databaseFind1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot1) {
                        for (DataSnapshot snapshot : snapshot1.getChildren()) {
                            
//                            Map map = (Map) snapshot.getValue(); // it stores the database value to this local variable
//                            System.out.println("Entring to loop");
                            if (snapshot.child("name").getValue().equals(nameEnteredToSearch)){
                                fa.setText(snapshot.child("fathersName").getValue().toString());
                                mo.setText(snapshot.child("mothersName").getValue().toString());
                                ge.setText(snapshot.child("gender").getValue().toString());
                                cn.setText(snapshot.child("contactNumber").getValue().toString());
                                break;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity3.this, "Eror in retriving data from database", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}