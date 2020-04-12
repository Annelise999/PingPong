package com.example.pp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppareilPhoto extends AppCompatActivity {
    // constante
    private static final int RETOUR_PRENDRE_PHOTO = 1;

    // Proprietes
    private Button btnPrendrePhoto;
    private ImageView imgAffichePhoto;
    private String photoPath = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appareil_photo);
        initActivity();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initActivity(){
        // récupération des objets graphiques
        btnPrendrePhoto = (Button)findViewById(R.id.btnPrendrePhoto);
        imgAffichePhoto = (ImageView)findViewById(R.id.imgAffichePhoto);
        // méthode pour gérer les événements


    }
    private void createOnClickBtnPrendrePhoto(){
        btnPrendrePhoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                prendreUnePhoto();

            }
        });
    }
    // Accès à l'appareil photo et mémorise dans un fichier temporaire
    private void prendreUnePhoto(){
        //cree un intent pour ouvrir une fenêtre pour prendre la photo
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // test pour contrôler que l'intent peut être géré
        if(intent.resolveActivity(getPackageManager()) != null){
            //créer un nom de fichier unique
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.createTempFile("photo"+time, ".jpg", photoDir);
                // enregistrer le chemin complet
                photoPath = photoFile.getAbsolutePath();
                // créer l'URI
                Uri photoURI = FileProvider.getUriForFile(AppareilPhoto.this,
                        AppareilPhoto.this.getApplicationContext().getPackageName()+".provider",
                        photoFile);
                // transfert URI vers l'intent pour enregistrement photo dans fichier temporaire
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                // ouvrir l'activity par rapport à l'intent
                startActivityForResult(intent, RETOUR_PRENDRE_PHOTO);



            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * retour de l'appel de l'appareil photo (startActivityForResult)
     * @param requestcode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestcode, int resultCode, Intent data){
        super.onActivityResult(requestcode, resultCode, data);
        // vérifie le bon code de retour et l'état du retour ok
        if (requestcode==RETOUR_PRENDRE_PHOTO && resultCode==RESULT_OK){
            //récupérer l'image
            Bitmap image = BitmapFactory.decodeFile(photoPath);
            // afficher l'image
            imgAffichePhoto.setImageBitmap(image);

        }


    }



}
