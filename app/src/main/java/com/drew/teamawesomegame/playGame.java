package com.drew.teamawesomegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class playGame extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);

        ImageButton chrisButton = findViewById(R.id.chrisButton);
        chrisButton.setOnClickListener(this);

        ImageButton alexxButton = findViewById(R.id.alexxButton);
        alexxButton.setOnClickListener(this);

        ImageButton drewButton = findViewById(R.id.drewButton);
        drewButton.setOnClickListener(this);

        ImageButton mattButton = findViewById(R.id.mattButton);
        mattButton.setOnClickListener(this);

        ImageButton randyButton = findViewById(R.id.randyButton);
        randyButton.setOnClickListener(this);

        ImageButton jeffButton = findViewById(R.id.jeffButton);
        jeffButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch(v.getId()){
           case R.id.exitButton:
               Intent i = new Intent(this, mainMenu.class);
               startActivity(i);
               break;
           case R.id.chrisButton:
               Intent j = new Intent(this, GameActivity.class);
               startActivity(j);
               break;
           case R.id.alexxButton:
               MainActivity.enemyNumber = 2;
               Intent k = new Intent(this, GameActivity.class);
               startActivity(k);
               break;
           case R.id.drewButton:
               MainActivity.enemyNumber = 3;
               Intent l = new Intent(this, GameActivity.class);
               startActivity(l);
               break;
           case R.id.mattButton:
               MainActivity.enemyNumber = 4;
               Intent m = new Intent(this, GameActivity.class);
               startActivity(m);
               break;
           case R.id.randyButton:
               MainActivity.enemyNumber = 5;
               Intent n = new Intent(this, GameActivity.class);
               startActivity(n);
               break;
           case R.id.jeffButton:
               MainActivity.enemyNumber = 6;
               Intent o = new Intent(this, GameActivity.class);
               startActivity(o);
               break;
       }
    }
}
