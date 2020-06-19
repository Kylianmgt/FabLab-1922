package com.example.fablab_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fablab_19.Objects.Request;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class FabLabInfoActivity extends AppCompatActivity {
    private static DatabaseReference dbref, dbref2;
    private TextView nbCommande, nbCommandeFinis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fablab_info);

        getSupportActionBar().hide();
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);

        this.dbref = FirebaseDatabase.getInstance().getReference("FabLabInfo").child("nb_commande_en_cours");
        this.dbref2 = FirebaseDatabase.getInstance().getReference("FabLabInfo").child("nb_commande_finis");
        nbCommande = findViewById(R.id.card_number_order_current);
        nbCommandeFinis = findViewById(R.id.card_number_order_end);
        updateInfo();
    }

    private void updateInfo() {
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nbCommandeEnCours = dataSnapshot.getValue().toString();
                //nbCommande.setText( nbCommandeEnCours );
                nbCommande.setText("Nous avons actuellement " + nbCommandeEnCours + " commandes en cours");
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        dbref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               String nbCommandeFini = dataSnapshot.getValue().toString();
               //nbCommandeFinis.setText(nbCommandeFini);
                nbCommandeFinis.setText("Nous avons actuellement "+ nbCommandeFini + " commandes fini");
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        //nbCommande.setText("Nous avons actuellement " + nbCommandeEnCours + " commandes en cours");
       // nbCommandeFinis.setText("Nous avons actuellement " + nbCommandeFini + " commandes finis");
    }
}