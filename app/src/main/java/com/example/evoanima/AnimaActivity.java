package com.example.evoanima;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class AnimaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anima);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the anima from the player
        Player player = (Player) getIntent().getSerializableExtra("player");

        // anima of the player
        Anima anima = player.getAnima();

        // assign the anima name to the text
        TextView textView = (TextView) findViewById(R.id.name_of_anima);
        textView.setText(anima.getName());

        // assign the anima level to the progress bar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(anima.getLevel());

        // assign the anima image to the imageView
        ImageView animaImage = (ImageView) findViewById(R.id.photo);
        Picasso.with(AnimaActivity.this).load(anima.getImage_url()).into(animaImage);
    }

    public void onClickRestart(View view) {
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }
}
