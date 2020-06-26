package com.example.fablab_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fablab_19.Objects.Request;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ornach.nobobutton.NoboButton;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class HomeActivity extends AppCompatActivity {

    private NoboButton createRequestButton, contactButton;
    private TextView homeTitle;
    private FirebaseListAdapter<Request> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);

        createRequestButton = findViewById(R.id.button_passer_commande);

        contactButton = findViewById(R.id.contact_button);
        homeTitle = findViewById(R.id.home_title);


        createRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requestActivity = new Intent( HomeActivity.this, RequestActivity.class);
                startActivity(requestActivity);
            }
        });
        showMyRequest();

        customizeHome();
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactActivity = new Intent( HomeActivity.this, ContactActivity.class);
                startActivity(contactActivity);
            }
        });


    }

    private void showMyRequest(){
        ListView listOfRequest = findViewById(R.id.list_of_request);
      Query query = FirebaseDatabase.getInstance().getReference("Request").orderByChild("email").equalTo(FirebaseAuth.getInstance().getCurrentUser().getEmail());
       FirebaseListOptions<Request> options = new FirebaseListOptions.Builder<Request>()
               .setQuery(query, Request.class)
               .setLayout(R.layout.card)
               .build();
       adapter = new  FirebaseListAdapter<Request>(options){
           @Override
           protected void populateView(@NonNull View v, @NonNull Request model, int position) {
               TextView requestResume, requestName, requestDate, requestCompany, companyCity, requestStatus;
               requestResume = (TextView) v.findViewById(R.id.card_resume);
               requestName = (TextView) v.findViewById(R.id.card_user);
               requestDate = (TextView) v.findViewById(R.id.card_start);
               requestCompany = (TextView) v.findViewById(R.id.card_title);
               companyCity = (TextView) v.findViewById(R.id.card_city);
               requestStatus = (TextView) v.findViewById(R.id.card_status);

               requestResume.setText(model.getRequestResume());
               requestName.setText(model.getName());
               requestCompany.setText(model.getCompanyName());
               companyCity.setText(model.getCompanyCity());
               requestDate.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getDate()));

              if (model.getStatus() == "En cours de traitement"){
                  requestStatus.setTextColor(Color.GREEN);
              }
              else{
                  requestStatus.setTextColor(Color.RED);
              }
               requestStatus.setText(model.getStatus());
               //messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));

           }
       };
       listOfRequest.setAdapter(adapter);
    }
    private void customizeHome(){
        homeTitle.setText("Bienvenue "+FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}