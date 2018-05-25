package com.drew.teamawesomegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class mainMenu extends AppCompatActivity implements View.OnClickListener {


        SharedPreferences prefs;
        String dataName = "myData";
        String intName = "myInt";
        int defaultInt = 0;
        public static int coinsSaved;
        public static int damageBoosts;
        public static int staminaBoosts;
        public static int healthBoosts;
        public static int rewardBoosts;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_menu);

            Button playButton = findViewById(R.id.playButton);
            playButton.setOnClickListener(this);

            Button statsButton = findViewById(R.id.statsButton);
            statsButton.setOnClickListener(this);

            Button storeButton = findViewById(R.id.storeButton);
            storeButton.setOnClickListener(this);

            Button aboutButton = findViewById(R.id.aboutButton);
            aboutButton.setOnClickListener(this);
            Log.d("*****", "dasdsa");

        }

    @Override
    protected void onResume() {
        super.onResume();

        prefs = getSharedPreferences(dataName, MODE_PRIVATE);
        coinsSaved = prefs.getInt(intName, defaultInt);
        damageBoosts = prefs.getInt(intName, defaultInt);
        staminaBoosts = prefs.getInt(intName, defaultInt);
        healthBoosts = prefs.getInt(intName, defaultInt);
        rewardBoosts = prefs.getInt(intName, defaultInt);


    }



        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.playButton:
                    Intent i = new Intent(this, playGame.class);

                    startActivity(i);
                    break;
                case R.id.statsButton:
                    Intent j = new Intent(this, playerStats.class);

                    startActivity(j);
                    break;
                case R.id.storeButton:
                    Intent k = new Intent(this, storePage.class);

                    startActivity(k);
                    break;
                case R.id.aboutButton:
                    Intent l = new Intent(this, aboutPage.class);

                    startActivity(l);
                default:
                    break;

            }
        }
}


