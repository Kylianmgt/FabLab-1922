package com.example.fablab_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class LoadingScreenActivity extends AppCompatActivity {
    private static int LOADING_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        getSupportActionBar().hide();
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(LoadingScreenActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },LOADING_TIME_OUT);
    }
}