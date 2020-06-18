package com.example.fablab_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fablab_19.Objects.Request;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.ornach.nobobutton.NoboButton;

import java.util.Date;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class RequestActivity extends AppCompatActivity {
private NoboButton sendRequestButton;
private TextInputLayout cityInput;
    private TextInputLayout companyNameInput;
    private EditText postalCodeInput;
    private TextInputLayout resumeInput, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        sendRequestButton = findViewById(R.id.send_request_button);
        cityInput = findViewById(R.id.city_input);
        companyNameInput = findViewById(R.id.company_name_input);
        postalCodeInput = findViewById(R.id.postal_address_input);
        resumeInput = findViewById(R.id.resume_input);
        phoneNumber = findViewById(R.id.phone_input);

        getSupportActionBar().hide();
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);



        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request();
                long now = new Date().getTime();
                request.setCompanyCity(cityInput.getEditText().getText().toString());
                request.setCompanyName(companyNameInput.getEditText().getText().toString());
                request.setCompanyPostalCode(postalCodeInput.getText().toString());
                request.setRequestResume(resumeInput.getEditText().getText().toString());
                request.setName(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                request.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                request.setStatus("En cours de traitement");
                request.setPhoneNumber(phoneNumber.getEditText().getText().toString());
                request.setDate(now);
                FirebaseDatabase.getInstance().getReference("Request").push().setValue(request);
                finish();
            }
        });
    }
}