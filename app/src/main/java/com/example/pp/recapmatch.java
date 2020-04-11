package com.example.pp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class recapmatch extends AppCompatActivity {

    private Handler handler;
    TextView nom_gagnant, temps_match, afficher_joueur1, afficher_joueur2, points_j1, points_j2, aces_j1, aces_j2, let_j1, let_j2, manches_j1, manches_j2, fautes_j1, fautes_j2;
    Button retour ;
    ImageButton local, gallery;
    Match current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recapmatch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        current = (Match) getIntent().getSerializableExtra("Match");

        current.PointsTotJ1(current.getPts_manche1_j1()+ current.getPts_manche2_j1()+ current.getPts_manche3_j1());
        current.PointsTotJ2(current.getPts_manche1_j2()+ current.getPts_manche2_j2()+ current.getPts_manche3_j2());

        //textview:
        nom_gagnant = findViewById(R.id.nom_gagnant);
        nom_gagnant.setText(current.getGagnant());
        temps_match = findViewById(R.id.temps_match);
        temps_match.setText(String.valueOf(current.getBalle()));
        afficher_joueur1 = findViewById(R.id.afficher_joueur1);
        afficher_joueur1.setText(current.getJoueur1());
        afficher_joueur2 = findViewById(R.id.afficher_joueur2);
        afficher_joueur2.setText(current.getJoueur2());
        points_j1 = findViewById(R.id.points_joueur1);
        points_j1.setText(String.valueOf(current.getPts_j1()));
        points_j2 = findViewById(R.id.points_joueur2);
        points_j2.setText(String.valueOf(current.getPts_j2()));
        aces_j1 = findViewById(R.id.ace_joueur1);
        aces_j1.setText(String.valueOf(current.getAces_j1()));
        aces_j2 = findViewById(R.id.ace_joueur2);
        aces_j2.setText(String.valueOf(current.getAces_j2()));
        manches_j1 = findViewById(R.id.manches_joueur1);
        manches_j1.setText(String.valueOf(current.getManches_j1()));
        manches_j2 = findViewById(R.id.manches_joueur2);
        manches_j2.setText(String.valueOf(current.getManches_j2()));
        fautes_j1 = findViewById(R.id.fautes_joueur1);
        fautes_j1.setText(String.valueOf(current.getFautes_j1()));
        fautes_j2 = findViewById(R.id.fautes_joueur2);
        fautes_j2.setText(String.valueOf(current.getFautes_j2()));

        let_j1= findViewById(R.id.let_joueur1);
        let_j1.setText(String.valueOf(current.getLet_j1()));
        let_j2= findViewById(R.id.let_joueur2);
        let_j2.setText(String.valueOf(current.getLet_j2()));



        //Button
        retour = findViewById(R.id.return_button);

        //ImageButton
        local = findViewById(R.id.local_button);
        gallery = findViewById(R.id.gallery_button);

        handler = new Handler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.previous_match) {
            Intent intent= new Intent (this, previousmatches.class);
            startActivity(intent);

        }
        if (id == R.id.match) {
            Intent intent= new Intent (this, MainActivity.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }

    public void myClickHandlerRetour(View view){
        if (view.getId() == R.id.return_button) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }




}
