package com.drew.teamawesomegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainMenu extends AppCompatActivity implements View.OnClickListener {

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

    }

    @Override
    public void onClick(View v) {

    }
}
