package com.drew.teamawesomegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button buttonQuit = findViewById(R.id.buttonQuit);
        buttonQuit.setOnClickListener(this);

        Button buttonLeft = findViewById(R.id.buttonLeft);
        buttonLeft.setOnClickListener(this);

        Button buttonRight = findViewById(R.id.buttonRight);
        buttonRight.setOnClickListener(this);

        Button buttonPunch1 = findViewById(R.id.test1);
        buttonPunch1.setOnClickListener(this);

        Button buttonPunch2 = findViewById(R.id.test2);
        buttonPunch2.setOnClickListener(this);

        Button buttonPunch3 = findViewById(R.id.test3);
        buttonPunch3.setOnClickListener(this);

        Button buttonPunch4 = findViewById(R.id.test4);
        buttonPunch4.setOnClickListener(this);

        Button buttonPunch5 = findViewById(R.id.test5);
        buttonPunch5.setOnClickListener(this);

        Button buttonPunch6 = findViewById(R.id.test6);
        buttonPunch6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonQuit:
                Intent i = new Intent(this, mainMenu.class);
                startActivity(i);
                break;
            case R.id.buttonLeft:
                //dodge();
                break;
            case R.id.buttonRight:
                //dodge();
                break;
            case R.id.test1:
                //punch();
                break;
            case R.id.test2:
                //punch();
                break;
            case R.id.test3:
                //punch();
                break;
            case R.id.test4:
                //punch();
                break;
            case R.id.test5:
                //punch();
                break;
            case R.id.test6:
                //punch();
                break;
                //TODO replace buttons with touch surface to detect if player is hitting enemy.
        }
    }
}
