package com.drew.teamawesomegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static int enemyNumber = 1;
    public static int coins = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.playButton);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, mainMenu.class);
        startActivity(i);
    }
}
