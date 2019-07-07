package com.wusafe.wosa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrustedContactsActivity extends AppCompatActivity {
    private Button add;
    private EditText phno,name;
    DatabaseReference RootRef;
    private String currentUserId;
    String userId,namer;
    private FirebaseAuth mAuth;
    String ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_contacts);
        add= (Button)findViewById(R.id.add);
        phno = (EditText)findViewById(R.id.phno);
        name = (EditText)findViewById(R.id.name);
        mAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();
        currentUserId = mAuth.getCurrentUser().getUid();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ph =  phno.getText().toString();
              namer = name.getText().toString();
              RootRef.child("Users").child(currentUserId).child("Trusted Contacts").child(namer).setValue(ph);


            }
        });

    }
}
