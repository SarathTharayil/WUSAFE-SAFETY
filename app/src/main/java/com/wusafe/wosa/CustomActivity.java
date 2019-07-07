package com.wusafe.wosa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomActivity extends AppCompatActivity {
    private EditText customtext;
    DatabaseReference RootRef;
    private String currentUserId;
    String userId;
    private FirebaseAuth mAuth;

    private Button custombtn;
    String cus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        customtext = (EditText)findViewById(R.id.customtext);
        custombtn = (Button)findViewById(R.id.custombutton);
        mAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();
        currentUserId = mAuth.getCurrentUser().getUid();


        Intent receive_i=getIntent();
        Bundle my_bundle_received=receive_i.getExtras();
        final String lat = my_bundle_received.get("lat").toString();
        final String log = my_bundle_received.get("log").toString();

        custombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cus = customtext.getText().toString();


                RootRef.child("requests").child(currentUserId).child("location").child("lat").setValue(lat);
                RootRef.child("requests").child(currentUserId).child("location").child("log").setValue(log);
                RootRef.child("requests").child(currentUserId).child("type").setValue("3");
                RootRef.child("requests").child(currentUserId).child("message").setValue(cus);
                RootRef.child("requests").child(currentUserId).child("uid").setValue(currentUserId);


            }
        });
    }
}
