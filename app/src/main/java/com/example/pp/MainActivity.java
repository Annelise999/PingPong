package com.example.pp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Handler handler;
    EditText entrer_joueur1, entrer_joueur2;
    TextView afficher_joueur1, afficher_joueur2;
    CheckBox servicej1, servicej2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choixjoueur);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        entrer_joueur1 = findViewById(R.id.entrer_joueur1);
        entrer_joueur2 = findViewById(R.id.entrer_joueur2);
        afficher_joueur1 = findViewById(R.id.afficher_joueur1);
        afficher_joueur2 = findViewById(R.id.afficher_joueur2);
        servicej1 = findViewById(R.id.service_j1);
        servicej2 = findViewById(R.id.service_j2);
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

    protected void myClickHandlerGo(View view){
        Intent intent = new Intent (this, Score.class);
        startActivity(intent);
    }

}
