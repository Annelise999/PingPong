package com.example.pp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {






    private Handler handler;
    EditText entrer_joueur1, entrer_joueur2;
    TextView afficher_joueur1, afficher_joueur2;
    CheckBox servicej1, servicej2;
    int service_joueur;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choixjoueur);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        entrer_joueur1 = findViewById(R.id.entrer_joueur1);
        entrer_joueur1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                afficher_joueur1.setText(entrer_joueur1.getText().toString());

            }
        });
        entrer_joueur2 = findViewById(R.id.entrer_joueur2);
        entrer_joueur2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                afficher_joueur2.setText(entrer_joueur2.getText().toString());
            }
        });
        afficher_joueur1 = findViewById(R.id.afficher_joueur1);
        afficher_joueur2 = findViewById(R.id.afficher_joueur2);
        servicej1 = findViewById(R.id.service_j1);
        servicej1.setText("a le service");
        servicej2 = findViewById(R.id.service_j2);
        servicej2.setText("a le service");
        handler = new Handler();



    }





    public void toastMessage (String message ){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

    public void myClickHandlerGo(View view){
        if (view.getId() == R.id.go) {
            String joueur1 = entrer_joueur1.getText().toString();
            String joueur2= entrer_joueur2.getText().toString();

            if (servicej1.isChecked())
            {
                service_joueur = 1;


            }
            else if (servicej2.isChecked())
            {
                service_joueur = 2;
            }
            Match current = new Match(joueur1, joueur2, service_joueur);
            Intent intent = new Intent(this, Score.class);
            intent.putExtra("Match", current);
            startActivity(intent);
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.service_j1:
                if (checked)
                    servicej2.setChecked(false);

                break;
            case R.id.service_j2:
                if (checked)
                    servicej1.setChecked(false);

                break;
        }
    }

}
