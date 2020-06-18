package com.example.fablab_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fablab_19.Objects.Message;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class ContactActivity extends AppCompatActivity {
    private ImageView submitButton;
    private EditText msgInput;
    private ListView conversationView;
    private FirebaseListAdapter<Message> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getSupportActionBar().hide();
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);

        submitButton = (ImageView) findViewById(R.id.submit_button);
        msgInput = findViewById(R.id.edit_text);
        conversationView = findViewById(R.id.list_of_message);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Contact").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(new Message(msgInput.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));
                msgInput.requestFocus();
            }
        });
        displayMessage();
    }


    private void displayMessage(){
        Query query = FirebaseDatabase.getInstance().getReference("Contact").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        FirebaseListOptions<Message> options = new FirebaseListOptions.Builder<Message>()

                .setQuery(query, Message.class)
                .setLayout(R.layout.message_bubble_layout)

                .build();
        adapter = new FirebaseListAdapter<Message>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Message model, int position) {
                TextView messageText, messageUser, messageTime;
                messageText = (TextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getUserName());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));

            }
        };
        conversationView.setAdapter(adapter);

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