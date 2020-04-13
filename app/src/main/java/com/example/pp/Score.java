package com.example.pp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Score extends AppCompatActivity {


    private Handler handler;
    private LocationManager locationManager;

    int PERMISSION_ID = 44;

    TextView name_joueur1, name_joueur2, name_j1, name_j2, score_j1_m1, score_j1_m2, score_j1_m3, score_j2_m1, score_j2_m2, score_j2_m3;
    TextView lat, lng;
    Button end, ace, faute, let, fautej1, fautej2, gagnej1, gagnej2;
    ImageButton local, gallery;
    Match current;
    DatabaseHelper mDataBaseHelper;
    FusedLocationProviderClient mFusedLocationClient;
    Geocoder geocoder;
    List<Address> addresses;
    String address;
    boolean toggling;
    private Bitmap image;
    ByteArrayOutputStream stream;
    byte[] byteArray;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Database
        mDataBaseHelper= new DatabaseHelper(this);


       locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //MAtch
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

        //Localisation
        lat = findViewById(R.id.lat);
        lng = findViewById(R.id.lng);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        geocoder = new Geocoder(this, Locale.getDefault());

        //Photo
        CheckPhotoPermission();
       

    }

    private void CheckPhotoPermission(){
        if (ContextCompat.checkSelfPermission(Score.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(Score.this, new String[] {Manifest.permission.CAMERA}, 100);
        }
    }

    public void myClickFoto(View view)
    {
        if (view.getId()== R.id.gallery_button)
        {
            prendreUnePhoto();
        }
    }

    private void prendreUnePhoto(){
        //cree un intent pour ouvrir une fenêtre pour prendre la photo
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);

    }

    @Override
    protected void onActivityResult(int requestcode, int resultCode, Intent data){
        super.onActivityResult(requestcode, resultCode, data);
        // vérifie le bon code de retour et l'état du retour ok
        if (requestcode==100 && resultCode==RESULT_OK){
            //récupérer l'image
            image = (Bitmap) data.getExtras().get("data");

            stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();




        }


    }

    public void myClickLocalisation (View view) {

        if(view.getId() == R.id.local_button){
            if (checkPermissions()) {
                if (isLocationEnabled()) {
                    mFusedLocationClient.getLastLocation().addOnCompleteListener(
                            new OnCompleteListener<Location>() {
                                @Override
                                public void onComplete(@NonNull Task<Location> task) {
                                    Location location = task.getResult();
                                    if (location == null) {
                                        toastMessage("localisation is null");
                                    } else {
                                        try {
                                            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                        }
                                        catch (Exception e)
                                        {
                                            toastMessage("probleme" + e);
                                        }
                                        address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                                        if(toggling == false) {
                                            lat.setText(address);
                                            lng.setText("");
                                            toggling= true;
                                        }
                                        else
                                        {
                                            lat.setText(location.getLatitude()+"");
                                            lng.setText(location.getLongitude()+"");
                                            toggling = false;
                                        }

                                    }
                                }
                            }
                    );
                } else {
                    Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            } else {
                requestPermissions();
            }
        }

    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Granted. Start getting the location information
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    toastMessage("localisation is null");
                                } else {


                                    lat.setText(location.getLatitude()+"");
                                    lng.setText(location.getLongitude()+"");
                                    current.setLat(location.getLatitude());
                                    current.setLng(location.getLongitude());


                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
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
            intent.putExtra("image", byteArray);
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
