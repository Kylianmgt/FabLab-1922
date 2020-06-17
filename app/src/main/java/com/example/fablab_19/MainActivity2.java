package com.example.fablab_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Mettre l'appli en plein écran
        getSupportActionBar().hide();
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);

    }
}