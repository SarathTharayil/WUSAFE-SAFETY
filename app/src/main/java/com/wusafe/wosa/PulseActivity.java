package com.wusafe.wosa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class PulseActivity extends AppCompatActivity {
    private String currentUserId;
    String userId;
    String pulsee;
    private FirebaseAuth mAuth;
    DatabaseReference RootRef;
    int[] ids = new int[100];
    TextView eg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse);
        eg = (TextView)findViewById(R.id.eg);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        Intent receive_i=getIntent();
        Bundle my_bundle_received=receive_i.getExtras();
        final String lat = my_bundle_received.get("lat").toString();
        final String log = my_bundle_received.get("log").toString();


        final DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference pulseval = RootRef.child("Users").child(currentUserId);


        pulseval.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String,Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("pulse")!=null){
                        pulsee = map.get("pulse").toString();
                        eg.setText(pulsee);
                        if (Integer.parseInt(pulsee) > 100){
                            RootRef.child("requests").child(currentUserId).child("uid").setValue(currentUserId);

                            RootRef.child("requests").child(currentUserId).child("location").child("lat").setValue(lat);
                            RootRef.child("requests").child(currentUserId).child("location").child("log").setValue(log);
                            RootRef.child("requests").child(currentUserId).child("type").setValue("4");

                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
