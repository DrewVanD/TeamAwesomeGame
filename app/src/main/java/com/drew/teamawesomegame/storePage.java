package com.drew.teamawesomegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class storePage extends AppCompatActivity implements View.OnClickListener{

    int itemCol = 1;
    int item = 1;
    int currentCoins = playerStats.coins;
    int currentPrice = 0;

    @Override
    protected void onStop(){
        super.onStop();
        finish();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.player.setVolume(100,100);
        MainActivity.player.start();

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

            TextView coinTotal = findViewById(R.id.coinsNum);
            coinTotal.setText("$" + currentCoins);


    }

    @Override
    public void onClick(View v) {
        TextView itemName = findViewById(R.id.frontPageText);

        switch(v.getId()){
            case R.id.exitButton:
                itemCol = 1;
                item = 1;
                onStop();
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


        switch(itemCol) {
            case 1:
                switch(item){
                    case 1:
                        itemImage.setImageResource(R.drawable.drink);
                        itemDesc.setText("Small Increase To Health(5)");
                        itemName.setText("Water Bottle");
                        itemPrice.setText("$100");
                        currentPrice = 100;
                        playerStats.healthMod += 5;
                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.drink2);
                        itemDesc.setText("Medium Increase To Health(20)");
                        itemName.setText("Energy Drink");
                        itemPrice.setText("$350");
                        currentPrice = 350;
                        playerStats.healthMod += 20;
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.drink3);
                        itemDesc.setText("Large Increase To Health(50)");
                        itemName.setText("Booster Drink");
                        itemPrice.setText("$900");
                        currentPrice = 900;
                        playerStats.healthMod += 50;
                        break;
                }
                break;
            case 2:
                switch(item){
                    case 1:
                        itemImage.setImageResource(R.drawable.trunks);
                        itemDesc.setText("Small Increase To Stamina(5)");
                        itemName.setText("Blue/White Trunks");
                        itemPrice.setText("$100");
                        currentPrice = 100;
                        playerStats.staminaMod += 5;
                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.trunks2);
                        itemDesc.setText("Medium Increase To Stamina(20)");
                        itemName.setText("Gold/Black Trunks");
                        itemPrice.setText("$350");
                        currentPrice = 350;
                        playerStats.staminaMod += 20;
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.trunks3);
                        itemDesc.setText("Large Increase To Stamina(50)");
                        itemName.setText("Pink/Gold Trunks");
                        itemPrice.setText("$900");
                        currentPrice = 900;
                        playerStats.staminaMod += 50;
                        break;

                }
                break;
            case 3:
                switch (item){
                    case 1:
                        itemImage.setImageResource(R.drawable.heavyglove);
                        itemDesc.setText("Small Increase To Damage(1)");
                        itemName.setText("Heavy Glove");
                        itemPrice.setText("$100");
                        currentPrice = 100;
                        playerStats.damageMod += 1;
                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.pelletgloves);
                        itemDesc.setText("Medium Increase To Damage(5)");
                        itemName.setText("Pellet Glove");
                        itemPrice.setText("$350");
                        currentPrice = 350;
                        playerStats.damageMod += 5;
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.barbedgloves);
                        itemDesc.setText("Large Increase To Damage(10)");
                        itemName.setText("Barbed Gloves");
                        itemPrice.setText("$900");
                        currentPrice = 900;
                        playerStats.staminaMod += 10;
                        break;
                }
                break;
            case 4:
                switch (item){
                    case 1:
                        itemImage.setImageResource(R.drawable.trainer);
                        itemDesc.setText("Small Increase To Rewards(10)");
                        itemName.setText("Coach Master Roshi");
                        itemPrice.setText("$200");
                        currentPrice = 200;
                        playerStats.rewardMod += 10;

                        break;
                    case 2:
                        itemImage.setImageResource(R.drawable.trainer2);
                        itemDesc.setText("Medium Increase To Rewards(50)");
                        itemName.setText("Coach Kernal Sandas");
                        itemPrice.setText("$950");
                        currentPrice = 950;
                        playerStats.rewardMod += 50;
                        break;
                    case 3:
                        itemImage.setImageResource(R.drawable.trainer3);
                        itemDesc.setText("Large Increase To Rewards(100)");
                        itemName.setText("Coach Burgher Kingg");
                        itemPrice.setText("$1800");
                        currentPrice = 1800;
                        playerStats.rewardMod += 100;
                        break;
                }
                break;
        }

    }
    public void purchase(){
        TextView coinTotal = findViewById(R.id.coinsNum);
        if(currentCoins >= currentPrice){
            Toast.makeText(getApplicationContext(),"Purchased", Toast.LENGTH_LONG).show();
            playerStats.coins -= currentPrice;
            currentCoins = playerStats.coins;
            coinTotal.setText("$" + currentCoins);

        }else{
            Toast.makeText(getApplicationContext(),"You Broke Mofo", Toast.LENGTH_LONG).show();
        }

    }

}
