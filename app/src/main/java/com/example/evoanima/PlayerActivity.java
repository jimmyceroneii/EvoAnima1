package com.example.evoanima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

public class PlayerActivity extends AppCompatActivity {
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText editText = (EditText) findViewById(R.id.player_name);
        String playerName = editText.getText().toString();

        player = new Player(playerName);
    }

    // how do I make sure this doesn't get clicked until the

    public void onClickSubmit(View view) {
        Intent intent = new Intent(this, ExploreActivityWithFirebase.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }
}
