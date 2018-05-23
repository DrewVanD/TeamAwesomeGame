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
        Bundle bundle = new Bundle();
        MainActivity.player.setVolume(0.25f,0.25f);
       switch(v.getId()){
           case R.id.exitButton:
               Intent i = new Intent(this, mainMenu.class);
               startActivity(i);
               break;
           case R.id.chrisButton:
               Body.bodyNumber = 2;
               Enemy.facenum = 0;
               //Enemy chris = new Enemy("Chris 'Holy' Jarino",200,200,10,100,100,1,0,0);
               Intent j = new Intent(this, GameActivity.class);

               bundle.putInt("Health",100);
               bundle.putInt("MaxHealth",100);
               bundle.putInt("DamagePerSwing",10);
               bundle.putInt("CoinReward",100);
               bundle.putInt("ExpReward",50);
               bundle.putInt("TimeBetweenSwings",5);
               bundle.putInt("FaceDamage",0);
               bundle.putInt("FaceNum",0);
               bundle.putString("EnemyName","Chris 'Holy' Jarino");
               j.putExtra("BUNDLE",bundle);
               startActivity(j);
               break;
           case R.id.alexxButton:
               Body.bodyNumber = 1;
               Enemy.facenum = 1;
               //Enemy alexx = new Enemy("Alexx 'Quanterooni' Quan",200,200,10,100,100,1,0,1);
               Intent k = new Intent(this, GameActivity.class);
               //Bundle alexx = new Bundle();
               bundle.putInt("Health",200);
               bundle.putInt("MaxHealth",200);
               bundle.putInt("DamagePerSwing",12);
               bundle.putInt("CoinReward",150);
               bundle.putInt("ExpReward",75);
               bundle.putInt("TimeBetweenSwings",4);
               bundle.putInt("FaceDamage",0);
               bundle.putInt("FaceNum",1);
               bundle.putString("EnemyName","Alexx 'Quanterooni' Quan");
               k.putExtra("BUNDLE",bundle);
               startActivity(k);
               break;
           case R.id.drewButton:
               Body.bodyNumber = 0;
               Enemy.facenum = 3;
               //Enemy drew = new Enemy("Drew Van Doom",200,200,10,100,100,1,0,3);
               Intent l = new Intent(this, GameActivity.class);
               //Bundle drew = new Bundle();
               bundle.putInt("Health",300);
               bundle.putInt("MaxHealth",300);
               bundle.putInt("DamagePerSwing",14);
               bundle.putInt("CoinReward",200);
               bundle.putInt("ExpReward",100);
               bundle.putInt("TimeBetweenSwings",3);
               bundle.putInt("FaceDamage",0);
               bundle.putInt("FaceNum",3);
               bundle.putString("EnemyName","Drew Van Doom");
               l.putExtra("BUNDLE",bundle);
               startActivity(l);
               break;
           case R.id.mattButton:
               Body.bodyNumber = 0;
               Enemy.facenum = 2;
               //Enemy matt = new Enemy("Matt 'Oca'  Agostino",200,200,10,100,100,1,0,2);
               Intent m = new Intent(this, GameActivity.class);
               //Bundle matt = new Bundle();
               bundle.putInt("Health",400);
               bundle.putInt("MaxHealth",400);
               bundle.putInt("DamagePerSwing",16);
               bundle.putInt("CoinReward",250);
               bundle.putInt("ExpReward",150);
               bundle.putInt("TimeBetweenSwings",2);
               bundle.putInt("FaceDamage",0);
               bundle.putInt("FaceNum",2);
               bundle.putString("EnemyName","Matt 'Oca' Agostino");
               m.putExtra("BUNDLE",bundle);
               startActivity(m);
               break;
           case R.id.randyButton:
               Body.bodyNumber = 1;
               Enemy.facenum = 4;
               //Enemy randy = new Enemy("Macho 'Stan The Van' Savage",200,200,10,100,100,1,0,4);
               Intent n = new Intent(this, GameActivity.class);
               bundle.putInt("Health",500);
               bundle.putInt("MaxHealth",500);
               bundle.putInt("DamagePerSwing",18);
               bundle.putInt("CoinReward",250);
               bundle.putInt("ExpReward",175);
               bundle.putInt("TimeBetweenSwings",2);
               bundle.putInt("FaceDamage",0);
               bundle.putInt("FaceNum",4);
               bundle.putString("EnemyName","Macho 'Stan The Van' Savage");
               startActivity(n);
               break;
           case R.id.jeffButton:
               Body.bodyNumber = 0;
               Enemy.facenum = 6;
               //Enemy jeff = new Enemy("Jeff 'The Demo Man' Gauvan",200,200,10,100,100,1,0,6);
               Intent o = new Intent(this, GameActivity.class);
               bundle.putInt("Health",1000);
               bundle.putInt("MaxHealth",1000);
               bundle.putInt("DamagePerSwing",20);
               bundle.putInt("CoinReward",300);
               bundle.putInt("ExpReward",200);
               bundle.putInt("TimeBetweenSwings",1);
               bundle.putInt("FaceDamage",0);
               bundle.putInt("FaceNum",6);
               bundle.putString("EnemyName","Jeff 'The Demo Man' Gauvan");
               startActivity(o);
               break;
       }
    }
}
