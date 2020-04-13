package com.example.pp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Gallery extends AppCompatActivity {

    private Button retour;
    private ImageView photo;
    byte[] byteArray;
    Bitmap laphoto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        retour= findViewById(R.id.GoBack);
        byteArray = getIntent().getByteArrayExtra("image");

        laphoto = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        //ImageView
        photo= findViewById(R.id.foto_match);
        photo.setImageBitmap(laphoto);

    }

    public void myClickHandlerRetour(View view){
        if (view.getId() == R.id.GoBack) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}
