package com.wusafe.wosa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComplaintActivity extends AppCompatActivity implements LocationListener {
    private Button medButton,phyButton;
    DatabaseReference RootRef;
    private String currentUserId;
    String userId;
    Location mLastLocation;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        mAuth = FirebaseAuth.getInstance();
        medButton = (Button)findViewById(R.id.medButton);
        userId = mAuth.getCurrentUser().getUid();
        phyButton = (Button)findViewById(R.id.phyButton);

        Intent receive_i=getIntent();
        Bundle my_bundle_received=receive_i.getExtras();
        final String lat = my_bundle_received.get("lat").toString();
        final String log = my_bundle_received.get("log").toString();

        currentUserId = mAuth.getCurrentUser().getUid();

        RootRef = FirebaseDatabase.getInstance().getReference();



        medButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    RootRef.child("requests").child(currentUserId).child("location").child("lat").setValue(lat);
                    RootRef.child("requests").child(currentUserId).child("location").child("log").setValue(log);
                    RootRef.child("requests").child(currentUserId).child("type").setValue("1");
                    RootRef.child("requests").child(currentUserId).child("uid").setValue(userId);

                }

        });

        phyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RootRef.child("requests").child(currentUserId).child("location").child("lat").setValue(lat);
                RootRef.child("requests").child(currentUserId).child("location").child("log").setValue(log);
                    RootRef.child("requests").child(currentUserId).child("type").setValue("2");
                    RootRef.child("requests").child(currentUserId).child("uid").setValue(userId);


            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {

         mLastLocation = location;






    }
}
