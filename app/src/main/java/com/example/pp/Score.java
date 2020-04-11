package com.example.pp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class Score extends AppCompatActivity {


    private Handler handler;

    TextView name_joueur1, name_joueur2, name_j1, name_j2, score_j1_m1, score_j1_m2, score_j1_m3, score_j2_m1, score_j2_m2, score_j2_m3;
    Button end, ace, faute, let, fautej1, fautej2, gagnej1, gagnej2;
    ImageButton local, gallery;
    Match current;
    DatabaseHelper mDataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataBaseHelper= new DatabaseHelper(this);

        current = (Match) getIntent().getSerializableExtra("Match");

        //textview:
        name_joueur1 = findViewById(R.id.name_joueur1);
        name_joueur2 = findViewById(R.id.name_joueur2);
        name_j1 = findViewById(R.id.name_j1);
        name_j2 = findViewById(R.id.name_j2);

        name_joueur1.setText(current.getJoueur1());
        name_j1.setText(current.getJoueur1());
        name_joueur2.setText(current.getJoueur2());
        name_j2.setText(current.getJoueur2());

        score_j1_m1 = findViewById(R.id.score_j1_m1);
        score_j1_m2 = findViewById(R.id.score_j1_m2);
        score_j1_m3 = findViewById(R.id.score_j1_m3);
        score_j2_m1 = findViewById(R.id.score_j2_m1);
        score_j2_m2 = findViewById(R.id.score_j2_m2);
        score_j2_m3 = findViewById(R.id.score_j2_m3);

        score_j1_m1.setText(String.valueOf(0));
        score_j2_m1.setText(String.valueOf(0));
        score_j1_m2.setText(String.valueOf(0));
        score_j2_m2.setText(String.valueOf(0));
        score_j1_m3.setText(String.valueOf(0));
        score_j2_m3.setText(String.valueOf(0));



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

    public void addData (Match m){
        boolean insertData = mDataBaseHelper.addData(m);
        if (insertData){
            toastMessage("Insert correctly");
        }
        else {
            toastMessage("Something went wrong");
        }

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

    public void myClickHandlerEnd(View view){
        if (view.getId() == R.id.end_button) {
            Intent intent = new Intent(this, recapmatch.class);
            intent.putExtra("Match", current);
            startActivity(intent);
        }
    }



    public void myClickHandlerMatch (View view)
    {
        int gagnant =0;

        switch(view.getId()) {
            case R.id.button_let:
                if (current.getJoueur_service() == 1) {
                    current.setLet_j1();
                } else if (current.getJoueur_service() == 2) {
                    current.setLet_j2();
                }
                break;

            case R.id.button_ace:

                current.setBalle();
                if (current.getJoueur_service() == 1) {
                    current.setAces_j1();
                    current.setPts_j1();

                } else if (current.getJoueur_service() == 2) {
                    current.setAces_j2();
                    current.setPts_j2();
                }
                current.changementServeur();

                break;
            case R.id.button_gagne_j1:
                current.setBalle();
                current.setPts_j1();
                current.changementServeur();
                break;
            case R.id.button_gagne_j2:
                current.setBalle();
                current.setPts_j2();
                current.changementServeur();
                break;
            case R.id.button_faute_j1:
                current.setBalle();
                current.setFautes_j1();
                current.setPts_j2();
                current.changementServeur();
                break;
            case R.id.button_faute_j2:
                current.setBalle();
                current.setFautes_j2();
                current.setPts_j1();
                current.changementServeur();
                break;
            default: System.out.println("error");

        }

        gagnant = current.CheckMancheAndWinner();


        if (gagnant == 1) {

            current.PointsTotJ1(current.getPts_manche1_j1()+ current.getPts_manche2_j1()+ current.getPts_manche3_j1());
            current.PointsTotJ2(current.getPts_manche1_j2()+ current.getPts_manche2_j2()+ current.getPts_manche3_j2());

            Intent intent = new Intent(this, recapmatch.class);
            intent.putExtra("Match", current);
            startActivity(intent);
            addData(current);
            //save match in the bdd
        }



        if( current.getCurrent_manche()== 1)
        {
            score_j1_m1.setText(String.valueOf(current.getPts_j1()));
            score_j2_m1.setText(String.valueOf(current.getPts_j2()));


        }
        else if(current.getCurrent_manche() == 2) {
            score_j1_m1.setText(String.valueOf(current.getPts_manche1_j1()));
            score_j2_m1.setText(String.valueOf(current.getPts_manche1_j2()));
            score_j1_m2.setText(String.valueOf(current.getPts_j1()));
            score_j2_m2.setText(String.valueOf(current.getPts_j2()));
        }
        else {
            score_j1_m2.setText(String.valueOf(current.getPts_manche2_j1()));
            score_j2_m2.setText(String.valueOf(current.getPts_manche2_j2()));
            score_j1_m3.setText(String.valueOf(current.getPts_j1()));
            score_j2_m3.setText(String.valueOf(current.getPts_j2()));
        }

    }





}
