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
               Body.bodyNumber = 2;
               Enemy chris = new Enemy("Chris 'Holy' Jarino",200,200,10,100,100,1,0,0);
               Intent j = new Intent(this, GameActivity.class);
               startActivity(j);
               break;
           case R.id.alexxButton:
               Body.bodyNumber = 1;
               Enemy alexx = new Enemy("Alexx 'Quanterooni' Quan",200,200,10,100,100,1,0,1);
               Intent k = new Intent(this, GameActivity.class);
               startActivity(k);
               break;
           case R.id.drewButton:
               Body.bodyNumber = 0;
               Enemy drew = new Enemy("Drew Van Doom",200,200,10,100,100,1,0,3);
               Intent l = new Intent(this, GameActivity.class);
               startActivity(l);
               break;
           case R.id.mattButton:
               Body.bodyNumber = 0;
               Enemy matt = new Enemy("Matt 'Oca'  Agostino",200,200,10,100,100,1,0,2);
               Intent m = new Intent(this, GameActivity.class);
               startActivity(m);
               break;
           case R.id.randyButton:
               Body.bodyNumber = 1;
               Enemy randy = new Enemy("Macho 'Stan The Van' Savage",200,200,10,100,100,1,0,4);
               Intent n = new Intent(this, GameActivity.class);
               startActivity(n);
               break;
           case R.id.jeffButton:
               Body.bodyNumber = 0;
               Enemy jeff = new Enemy("Jeff 'The Demo Man' Gauvan",200,200,10,100,100,1,0,6);
               Intent o = new Intent(this, GameActivity.class);
               startActivity(o);
               break;
       }
    }
}
