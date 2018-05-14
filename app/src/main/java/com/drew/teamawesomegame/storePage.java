package com.drew.teamawesomegame;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class storePage extends AppCompatActivity implements View.OnClickListener{

    int itemCol = 1;
    int item = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);

        Button button = findViewById(R.id.exitButton);
        button.setOnClickListener(this);

        Button upButton = findViewById(R.id.upButton);
        upButton.setOnClickListener(this);

        Button downButton = findViewById(R.id.downButton);
        downButton.setOnClickListener(this);

        Button purchaseButton = findViewById(R.id.purchaseButton);
        purchaseButton.setOnClickListener(this);

        Button healthButton = findViewById(R.id.healthButton);
        healthButton.setOnClickListener(this);

        Button staminaButton = findViewById(R.id.staminaButton);
        staminaButton.setOnClickListener(this);

        Button glovesButton = findViewById(R.id.glovesButton);
        glovesButton.setOnClickListener(this);

        Button trainButton = findViewById(R.id.trainButton);
        trainButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.exitButton:
                itemCol = 1;
                item = 1;
                Intent i = new Intent(this, mainMenu.class);
                startActivity(i);
                break;
            case R.id.upButton:
                if(item < 3) {
                    item++;
                }
                else{
                    item = 1;
                }
                setImage();
                break;
            case R.id.downButton:
                if(item > 1) {
                    item--;
                }
                else{
                    item = 3;
                }
                setImage();
                break;
            case R.id.healthButton:
                itemCol = 1;
                item = 1;
                setImage();
                break;
            case R.id.staminaButton:
                itemCol = 2;
                item = 1;
                setImage();
                break;
            case R.id.glovesButton:
                itemCol = 3;
                item = 1;
                setImage();
                break;
            case R.id.trainButton:
                itemCol = 4;
                item = 1;
                setImage();
                break;

        }
    }

    public void setImage(){
        ImageView itemImage = findViewById(R.id.itemView);
        TextView itemPrice = findViewById(R.id.itemPrice);
        TextView itemName = findViewById(R.id.itemName);

        switch(itemCol) {
            case 1:
                switch(item){
                    case 1:
                        itemImage.setImageResource(R.drawable.drink);
                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.trunks);
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.trainer);
                        break;

                }
            break;
            case 2:
                switch(item){
                    case 1:
                        itemImage.setImageResource(R.drawable.drink);
                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.trunks);
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.trainer);
                        break;
                }
            break;
            case 3:
                switch (item){
                    case 1:
                        itemImage.setImageResource(R.drawable.heavyglove);
                        itemName.setText("Heavy Glove");
                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.pelletgloves);
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.barbedgloves);
                        break;
                }
            break;
            case 4:
                switch (item){
                    case 1:
                        itemImage.setImageResource(R.drawable.drink);
                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.trunks);
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.trainer);
                        break;
                }
            break;
        }
    }
}
