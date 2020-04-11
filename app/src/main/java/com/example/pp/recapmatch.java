package com.example.pp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class recapmatch extends AppCompatActivity {


    private Handler handler;
    TextView nom_gagnant, temps_match, afficher_joueur1, afficher_joueur2, points_j1, points_j2, aces_j1, aces_j2, let_j1, let_j2, manches_j1, manches_j2, fautes_j1, fautes_j2;
    Button retour ;
    ImageButton local, gallery;
    Match current;
    DatabaseHelper mDataBaseHelper;


    private ArrayList<Match> listData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recapmatch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        current = (Match) getIntent().getSerializableExtra("Match");



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

        mDataBaseHelper= new DatabaseHelper(this);


        populateRecap ();
    }

    public void populateRecap (){
        listData = new ArrayList<>();
        Cursor data = mDataBaseHelper.getData();
        while (data.moveToNext()){

            listData.add(new Match(data.getString(1), data.getString(2), data.getInt(3), data.getInt(4), data.getInt(5), data.getInt(6), data.getInt(7), data.getInt(8), data.getInt(9), data.getInt(10), data.getInt(11), data.getInt(12), data.getInt(13)));

        }



    }

    public void RecupMatch (int i)
    {

        nom_gagnant.setText(String.valueOf(listData.get(listData.size()-i).getGagnant()));
        temps_match.setText(String.valueOf(listData.get(listData.size()-i).getBalle()));
        afficher_joueur1.setText(listData.get(listData.size()-i).getJoueur1());
        afficher_joueur2.setText(listData.get(listData.size()-i).getJoueur2());
        points_j1.setText(String.valueOf(listData.get(listData.size()-i).getPts_j1()));
        points_j2.setText(String.valueOf(listData.get(listData.size()-i).getPts_j2()));
        aces_j1.setText(String.valueOf(listData.get(listData.size()-i).getAces_j1()));
        aces_j2.setText(String.valueOf(listData.get(listData.size()-i).getAces_j2()));
        manches_j1.setText(String.valueOf(listData.get(listData.size()-i).getManches_j1()));
        manches_j2.setText(String.valueOf(listData.get(listData.size()-i).getManches_j2()));
        fautes_j1.setText(String.valueOf(listData.get(listData.size()-i).getFautes_j1()));
        fautes_j2.setText(String.valueOf(listData.get(listData.size()-i).getFautes_j2()));
        let_j1.setText(String.valueOf(listData.get(listData.size()-i).getLet_j1()));
        let_j2.setText(String.valueOf(listData.get(listData.size()-i).getLet_j2()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recap, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.match_1) {
            RecupMatch(1);
        }
        if (id == R.id.match_2) {
            RecupMatch(2);
        }
        if (id == R.id.match_3) {
            RecupMatch(3);
        }
        if (id == R.id.match_4) {
            RecupMatch(4);
        }
        if (id == R.id.match_5) {
            RecupMatch(5);
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
