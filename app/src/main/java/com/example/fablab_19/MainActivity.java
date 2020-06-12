package com.example.fablab_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.snackbar.Snackbar;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class MainActivity extends AppCompatActivity {
    private static int SIGN_IN_REQUEST_CODE=1;
    private ScrollView activity_main;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                Snackbar.make(activity_main,"Successfully signed in.Welcome!", Snackbar.LENGTH_SHORT).show();

            }
            else{
                Snackbar.make(activity_main,"We couldn't sign you in.Please try again later", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);

        activity_main = (ScrollView) findViewById(R.id.activity_main);

        if (FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_REQUEST_CODE);
        }
        else{
            Snackbar.make(activity_main,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
            //Load content
        }

    }
}
