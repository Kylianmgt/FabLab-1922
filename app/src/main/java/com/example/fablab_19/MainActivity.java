package com.example.fablab_19;

        import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
        import android.view.View;
        import android.widget.ScrollView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.snackbar.Snackbar;
        import com.ornach.nobobutton.NoboButton;

        import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;


public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE=1;


    //Elements du layout
    private ScrollView activity_main;
    //private Button inscription_connexion;
    private NoboButton login_logout;
    private NoboButton info_fablab;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                Snackbar.make(activity_main,"Vous êtes bien connecté ! Bienvenue !", Snackbar.LENGTH_SHORT).show();
                Intent homeActivity = new Intent( MainActivity.this, HomeActivity.class );
                startActivity(homeActivity);

            }
            else{
                Snackbar.make(activity_main,"Erreur", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mettre l'appli en plein écran
        getSupportActionBar().hide();
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);


       // Les variables du layout
        activity_main = (ScrollView) findViewById(R.id.activity_main);
        login_logout = (NoboButton) findViewById((R.id.button_login_logout));
        info_fablab = (NoboButton) findViewById((R.id.button_info_fablab));


        //Inscription via authentification Google (via FirebaseAuth)
        login_logout.setOnClickListener(new View.OnClickListener() {
    @Override
             public void onClick(View v) {
                 if (FirebaseAuth.getInstance().getCurrentUser()==null){
                      startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_REQUEST_CODE);
                     }
                 else{
                     Snackbar.make(activity_main,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
                     Intent homeActivity = new Intent( MainActivity.this, HomeActivity.class );
                     startActivity(homeActivity);
        }
    }
});

        info_fablab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info_Fab_lab = new Intent(MainActivity.this, Fab_lab_info.class);
                startActivity(info_Fab_lab);
            }
        });
    }
}
