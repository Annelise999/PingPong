package com.example.pp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;

public class Score extends AppCompatActivity {


    private Handler handler;

    TextView name_joueur1, name_joueur2, name_j1, name_j2, score_j1_m1, score_j1_m2, score_j1_m3, score_j2_m1, score_j2_m2, score_j3_m3;
    Button end, ace, faute, let, fautej1, fautej2, gagnej1, gagnej2;
    ImageButton local, gallery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //textview:
        name_joueur1 = findViewById(R.id.name_joueur1);
        name_joueur2 = findViewById(R.id.name_joueur2);
        name_j1 = findViewById(R.id.name_j1);
        name_j2 = findViewById(R.id.name_j2);
        score_j1_m1 = findViewById(R.id.score_j1_m1);
        score_j1_m2 = findViewById(R.id.score_j1_m2);
        score_j1_m3 = findViewById(R.id.score_j1_m3);
        score_j2_m1 = findViewById(R.id.score_j2_m1);
        score_j2_m2 = findViewById(R.id.score_j2_m2);
        score_j3_m3 = findViewById(R.id.score_j2_m3);

        //Button
        end = findViewById(R.id.end_button);
        ace = findViewById(R.id.button_ace);
        let = findViewById(R.id.button_let);
        gagnej1 = findViewById(R.id.button_gagne_j1);
        gagnej2 = findViewById(R.id.button_gagne_j2);
        fautej1 = findViewById(R.id.button_faute_j1);
        fautej2 = findViewById(R.id.button_faute_j2);

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
        if (id == R.id.previous_matches) {
            Intent intent= new Intent (this, previousmatches.class);
            startActivity(intent);

        }
        if (id == R.id.match) {
            Intent intent= new Intent (this, MainActivity.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }



}
