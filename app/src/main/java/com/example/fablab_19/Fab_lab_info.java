package com.example.fablab_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.fablab_19.Objects.Request;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class Fab_lab_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fablab_info);

        getSupportActionBar().hide();
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
    }
/*
    private void showInfoFab(){
        ListView listOfInfo = findViewById(R.id.list_of_info);
        FirebaseListOptions<Request> options = new FirebaseListOptions.Builder<Request>()
                .setLayout(R.layout.infofablab)
                .build();

       adapter = new FirebaseListAdapter<Request>(options)

    }*/
}