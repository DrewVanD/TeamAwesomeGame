package com.drew.teamawesomegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class playerStats extends AppCompatActivity implements View.OnClickListener{

    public static int playerHealth = 100;
    public static int playerMaxHealth = 100;
    public static int baseDamage = 10;
    public static int playerStam = 100;
    public static int playerMaxStam = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        Button button = findViewById(R.id.exitButton);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, mainMenu.class);
        startActivity(i);
    }
}
