package com.drew.teamawesomegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class playerStats extends AppCompatActivity implements View.OnClickListener{

    public static float playerHealth = 100;
    public static float playerMaxHealth = 100;
    public static float baseDamage = 10;
    public static float playerStam = 100;
    public static float playerMaxStam = 100;
    public static int playerStamRegen = 5;
    public static int PunchCost = 20;


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
