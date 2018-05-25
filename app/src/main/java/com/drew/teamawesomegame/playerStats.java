package com.drew.teamawesomegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class playerStats extends AppCompatActivity implements View.OnClickListener{

    public static float playerHealth = 100;
    public static float playerMaxHealth = 100;
    public static float baseDamage = 10;
    public static float playerStam = 1000;//ADJUSTED FOR TESTING
    public static float playerMaxStam = 1000;
    public static int playerStamRegen = 5;
    public static int PunchCost = 20;
    public static int coins = 0;
    public static int damageMod = 0;
    public static int staminaMod = 0;
    public static int healthMod = 0;
    public static int rewardMod = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        Button button = findViewById(R.id.exitButton);
        button.setOnClickListener(this);

        TextView money = findViewById(R.id.moneyStat);
        money.setText("$" + coins);

        TextView moneyBoost = findViewById(R.id.moneyBoost);
        moneyBoost.setText("+$" + rewardMod);

        TextView damage = findViewById(R.id.damage);
        damage.setText(baseDamage + "/hit");

        TextView damageBoost = findViewById(R.id.damageBoost);
        damageBoost.setText("+" + damageMod + "/hit");

        TextView stamina = findViewById(R.id.stamina);
        stamina.setText("" + playerStam);

        TextView staminaBoost = findViewById(R.id.staminaBoost);
        staminaBoost.setText("+" + staminaMod);

        TextView health = findViewById(R.id.health);
        health.setText(playerHealth + "HP");

        TextView healthBoost = findViewById(R.id.healthBoost);
        healthBoost.setText("+" + healthMod);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, mainMenu.class);
        startActivity(i);
    }
}
