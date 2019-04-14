package com.mine.firstapp.mine;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                final Intent mainIntent =new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(mainIntent);
            }
        }, 3000);
    }
}
