package com.wusafe.wosa;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import static android.content.Context.MODE_PRIVATE;

public class SplashScreenActivity extends AppCompatActivity {

    private static int Splash_Screen_Time = 3000;
    private Boolean isFirstRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent loginIntent = new Intent(SplashScreenActivity.this, loginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            },Splash_Screen_Time);


        }

    }
