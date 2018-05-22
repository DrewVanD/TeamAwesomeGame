package com.drew.teamawesomegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class storePage extends AppCompatActivity implements View.OnClickListener{

    int itemCol = 1;
    int item = 1;
    int coins = 1;
    int currentCoins = 1000;
    int currentPrice = 0;
    TextView coinsNum;

    boolean canBuy = false;

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

        //coinsNum.setText(currentCoins);

    }

    @Override
    public void onClick(View v) {
        TextView itemName = findViewById(R.id.frontPageText);

        switch(v.getId()){
            case R.id.exitButton:
                itemCol = 1;
                item = 1;
                Intent i = new Intent(this, mainMenu.class);
                startActivity(i);
                break;
            case R.id.upButton:
                itemName.setText(" ");
                if(item < 3) {
                    item++;
                }
                else{
                    item = 1;
                }
                setImage();
                break;
            case R.id.downButton:
                itemName.setText(" ");
                if(item > 1) {
                    item--;
                }
                else{
                    item = 3;
                }
                setImage();
                break;
            case R.id.healthButton:
                itemName.setText(" ");
                itemCol = 1;
                item = 1;
                setImage();
                break;
            case R.id.staminaButton:
                itemName.setText(" ");
                itemCol = 2;
                item = 1;
                setImage();
                break;
            case R.id.glovesButton:
                itemName.setText(" ");
                itemCol = 3;
                item = 1;
                setImage();
                break;
            case R.id.trainButton:
                itemName.setText(" ");
                itemCol = 4;
                item = 1;
                setImage();
                break;
            case R.id.purchaseButton:
                itemName.setText(" ");
                purchase();
                break;
        }
    }

    public void setImage(){
        ImageView itemImage = findViewById(R.id.itemView);
        TextView itemPrice = findViewById(R.id.itemPrice);
        //can change price anytime
        TextView itemName = findViewById(R.id.itemName);
        TextView itemDesc = findViewById(R.id.itemDesc);
        coinsNum.setText(currentCoins);
        switch(itemCol) {
            case 1:
                switch(item){
                    case 1:
                        itemImage.setImageResource(R.drawable.drink);
                        itemDesc.setText("Small Increase To Health");
                        itemName.setText("Water Bottle");
                        itemPrice.setText("$100");
                        currentPrice = 100;

                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.drink2);
                        itemDesc.setText("Medium Increase To Health");
                        itemName.setText("Energy Drink");
                        itemPrice.setText("$200");
                        currentPrice = 200;
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.drink3);
                        itemDesc.setText("Large Increase To Health");
                        itemName.setText("Booster Drink");
                        itemPrice.setText("$300");
                        currentPrice = 300;
                        break;
                }
                break;
            case 2:
                switch(item){
                    case 1:
                        itemImage.setImageResource(R.drawable.trunks);
                        itemDesc.setText("Small Increase To Stamina");
                        itemName.setText("Blue/White Trunks");
                        itemPrice.setText("$100");
                        currentPrice = 100;
                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.trunks2);
                        itemDesc.setText("Medium Increase To Stamina");
                        itemName.setText("Gold/Black Trunks");
                        itemPrice.setText("$200");
                        currentPrice = 200;
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.trunks3);
                        itemDesc.setText("Large Increase To Stamina");
                        itemName.setText("Pink/Gold Trunks");
                        itemPrice.setText("$300");
                        currentPrice = 300;
                        break;

                }
                break;
            case 3:
                switch (item){
                    case 1:
                        itemImage.setImageResource(R.drawable.heavyglove);
                        itemDesc.setText("Small Increase To Damage");
                        itemName.setText("Heavy Glove");
                        itemPrice.setText("$100");
                        currentPrice = 100;
                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.pelletgloves);
                        itemDesc.setText("Medium Increase To Damage");
                        itemName.setText("Pellet Glove");
                        itemPrice.setText("$200");
                        currentPrice = 200;
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.barbedgloves);
                        itemDesc.setText("Large Increase To Damage");
                        itemName.setText("Barbed Gloves");
                        itemPrice.setText("$300");
                        currentPrice = 300;
                        break;
                }
                break;
            case 4:
                switch (item){
                    case 1:
                        itemImage.setImageResource(R.drawable.trainer);
                        itemDesc.setText("Small Increase To Rewards");
                        itemName.setText("Coach Master Roshi");
                        itemPrice.setText("$200");
                        currentPrice = 200;
                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.trainer2);
                        itemDesc.setText("Medium Increase To Rewards");
                        itemName.setText("Coach Kernal Sandas");
                        itemPrice.setText("$400");
                        currentPrice = 400;
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.trainer3);
                        itemDesc.setText("Large Increase To Rewards");
                        itemName.setText("Coach Burgher Kingg");
                        itemPrice.setText("$600");
                        currentPrice = 600;
                        break;
                }
                break;
        }
    }
    public void purchase(){

        if(currentPrice <= currentCoins){
            canBuy = true;
        }
        else if(currentPrice >= currentCoins) {
            canBuy = false;
        }
        if(canBuy){
            currentCoins -= currentPrice;
        }
    }
    public void coinsNum(){

    }
}
