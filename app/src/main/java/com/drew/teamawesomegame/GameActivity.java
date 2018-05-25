package com.drew.teamawesomegame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    Canvas canvas;
    SpriteView spriteView;
    boolean running = true;
    //SoundsPool
    private SoundPool soundPool;
    int realPunch = -1;
    int PUNCH = -1;
    int jabPunch = -1;
    int Hit_Hurt = -1;
    int Hit_Hurt3 = -1;
    int Hit_Hurt4 = -1;
    int Hit_Hurt5 = -1;
    int swoosh = -1;
    int gloveHit = -1;
    //Enemy Class
    volatile Enemy currentEnemy;

    //Shared Prefs
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String dataName = "MyData";
    String intName = "MyInt";
    int coinsSaved;
    int damageBoosts;
    int staminaBoosts;
    int healthBoosts;
    int rewardBoosts;

    @Override
    protected void onPause() {
        running = false;
        super.onPause();
        spriteView.pause();
        MainActivity.player.pause();


    }

    @Override
    protected void onResume() {
        super.onResume();
        spriteView.resume();
        MainActivity.player.start();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        prefs = getSharedPreferences(dataName, MODE_PRIVATE);
        editor = prefs.edit();

        Bundle bundle = getIntent().getBundleExtra("BUNDLE");
        //Enemy Stats
        int health = bundle.getInt("Health",0);
        int maxHealth = bundle.getInt("MaxHealth", 0);
        int damagePerSwing = bundle.getInt("DamagePerSwing", 0);
        int coinReward = bundle.getInt("CoinReward", 0);
        int expReward = bundle.getInt("ExpReward",0);
        int timeBetweenSwings = bundle.getInt("TimeBetweenSwings",0);
        int faceDamage = bundle.getInt("FaceDamage",0);
        int faceNum = bundle.getInt("FaceNum",0);
        String enemyName = bundle.getString("EnemyName");
        currentEnemy = new Enemy(enemyName,health,maxHealth,damagePerSwing,coinReward,expReward,timeBetweenSwings,faceDamage,faceNum);

        spriteView = new SpriteView(this);
        setContentView(spriteView);

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        try {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("realPunch.wav");
            realPunch = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("PUNCH.wav");
            PUNCH = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("jabPunch.wav");
            jabPunch = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Hit_Hurt.wav");
            Hit_Hurt = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Hit_Hurt3.wav");
            Hit_Hurt3 = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Hit_Hurt4.wav");
            Hit_Hurt4 = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Hit_Hurt5.wav");
            Hit_Hurt5 = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("glovehit.wav");
            gloveHit = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Swoosh.wav");
            swoosh = soundPool.load(descriptor,0);

        } catch (IOException e) {
        }
    }


    class SpriteView extends SurfaceView implements  Runnable {

        Thread thread = null;
        final SurfaceHolder holder;
        final Paint paint;

        //Bitmaps
        Bitmap bmp;
        Bitmap bit;
        Bitmap bim;
        Bitmap rGlove;
        Bitmap lGlove;
        Bitmap jiff;
        Bitmap ex;
        Bitmap dod;
        Bitmap dod1;

        //Constructors
        Exit exit;
        Dodge leftDod;
        Dodge rightDod;
        jeffBartender jeff;
        Body character;
        Face face;
        Glove LGlove;
        Glove RGlove;
        BackGround background;
        Display display;

        //Timers
        long stamTimer = 0;
        long stamTime = 1000;
        long punchTimer = 0;
        long punchTime = Enemy.timeBetweenSwings * 1000;
        long dodgetimer = 0;
        long waitTime = 0;
        long waitTimer = 3000;
        long lastFrameTime;
        long deltaTime;

        //Booleans
        boolean punched = false;
        boolean enemypunch = false;
        boolean punchanim = false;
        boolean scaleUp = true;
        boolean dodamage = true;
        boolean dodge = false;
        boolean fightOver = false;

        //Player Stats
        float playerHealth = playerStats.playerHealth + playerStats.healthMod;
        float playerMaxHealth = playerStats.playerMaxHealth + playerStats.healthMod;
        float playerStam = playerStats.playerStam + playerStats.staminaMod;
        float playerMaxStam = playerStats.playerMaxStam + playerStats.staminaMod;
        float playerStamRegen = playerStats.playerStamRegen;
        int punchCost = playerStats.PunchCost;
        boolean canPunch = true;
        float enemyPercentage = Enemy.health / Enemy.maxHealth;
        float playerHealthPercentage = playerHealth / playerMaxHealth;
        float playerStamPercentage = playerStam / playerMaxStam;

        //Canvas
        int screenWidth;
        int screenHeight;
        int fps;
        int rvy = 4;
        int lvy = 4;
        int gloveNum = 1;
        float scale = 1.0f;

        //Experience
        int exp = 0;
        int playerlvl = 1;
        int expincrement = 1000 + (500 * playerlvl);


        public SpriteView(Context context) {
            super(context);

            holder = getHolder();
            paint = new Paint();
            display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
            screenWidth = size.x;

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.body2);
            bit = BitmapFactory.decodeResource(getResources(), R.drawable.ring2);
            bim = BitmapFactory.decodeResource(getResources(), R.drawable.faces);
            rGlove = BitmapFactory.decodeResource(getResources(), R.drawable.rglove);
            lGlove = BitmapFactory.decodeResource(getResources(), R.drawable.lglove);
            jiff = BitmapFactory.decodeResource(getResources(),R.drawable.jeffbackground);
            dod = BitmapFactory.decodeResource(getResources(),R.drawable.leftdodge);
            dod1 = BitmapFactory.decodeResource(getResources(), R.drawable.rightdodge);

            ex = BitmapFactory.decodeResource(getResources(), R.drawable.exit);

            background = new BackGround(bit);

            background.x = 0;
            background.y = 0;

            character = new Body(bmp);
            character.x = (screenWidth / 2) - (character.width / 2);
            character.y = (background.height / 2) + (character.height / 2);

            face = new Face(bim);
            face.x = (screenWidth / 2) - (face.width / 2) + (screenWidth / 100);
            face.y = background.height / 2;

            RGlove = new Glove(rGlove);
            RGlove.x = (screenWidth / 5) * 3;
            RGlove.y = (background.height / 2) + (RGlove.height) - 100;

            LGlove = new Glove(lGlove);
            LGlove.x =screenWidth / 13;
            LGlove.y = (background.height / 2) + (LGlove.height) - 100;

            jeff = new jeffBartender(jiff);
            jeff.addAnimation("bar",0,3,7,110,96,true);
            jeff.setAnimation("bar");

            jeff.x = (screenWidth / 5) * 3;
            jeff.y = screenHeight / 14;

            exit = new Exit(ex);
            exit.x = (screenWidth/2) - (exit.width/2);
            exit.y = screenHeight - exit.height;

            leftDod = new Dodge(dod);
            leftDod.x = 0;
            leftDod.y = screenHeight - leftDod.height;

            rightDod = new Dodge(dod1);
            rightDod.x = screenWidth - rightDod.width;
            rightDod.y = screenHeight - rightDod.height;

        }

        public void damageEnemy(){

            if (Enemy.health <= Enemy.maxHealth / 2){
                Enemy.hurt = 1;
            }
            else {
                Enemy.hurt = 0;
            }
            if(Enemy.health <= 0)
            {
                exp += currentEnemy.expReward + playerStats.rewardMod;
                playerStats.coins += currentEnemy.coinReward + playerStats.rewardMod;
                Toast.makeText(getApplicationContext(),currentEnemy.enemyName + " Dead"
                                                           + "\nCoin Reward: " + (currentEnemy.coinReward + playerStats.rewardMod)
                                                            + "\nExp Reward: " + currentEnemy.expReward, Toast.LENGTH_LONG).show();
                coinsSaved = playerStats.coins;
                damageBoosts = playerStats.damageMod;
                staminaBoosts = playerStats.staminaMod;
                healthBoosts = playerStats.healthMod;
                rewardBoosts = playerStats.rewardMod;
                editor.putInt(intName, coinsSaved);
                editor.putInt(intName, damageBoosts);
                editor.putInt(intName, staminaBoosts);
                editor.putInt(intName, healthBoosts);
                editor.putInt(intName,rewardBoosts);
                editor.commit();
                fightOver = true;
            }
            if (dodamage) {
                Enemy.health -= playerStats.baseDamage + playerStats.damageMod;
            }
            enemyPercentage = Enemy.health / Enemy.maxHealth;

        }

        public void damagePlayer(){
            Toast.makeText(getApplicationContext(), "Ouch", Toast.LENGTH_LONG).show();
            enemypunch = true;
            punchanim = true;
            if(playerHealth <= 0){
                coinsSaved = playerStats.coins;
                damageBoosts = playerStats.damageMod;
                staminaBoosts = playerStats.staminaMod;
                healthBoosts = playerStats.healthMod;
                rewardBoosts = playerStats.rewardMod;
                editor.putInt(intName, coinsSaved);
                editor.putInt(intName, damageBoosts);
                editor.putInt(intName, staminaBoosts);
                editor.putInt(intName, healthBoosts);
                editor.putInt(intName,rewardBoosts);
                editor.commit();
                fightOver = true;
            }
            if (!dodge) {
                playerHealth -= currentEnemy.damage;
            }

            Random glove = new Random();
            gloveNum = glove.nextInt(2) + 1;

            playerHealthPercentage = playerHealth / playerMaxHealth;
        }

        public void levelUp() {
            if (exp >= expincrement){
                playerStats.playerMaxHealth += 10;
                playerStats.playerMaxStam += 10;
                playerStats.baseDamage += 1;
                exp = 0;
                playerlvl ++;
                Toast.makeText(getApplicationContext(),"LEVEL UP!", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction() & MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    if (x >= exit.x && x <= exit.x + exit.width && y >= exit.y && y <= exit.y + exit.width){
                        fightOver = true;
                    }

                    if (x >= RGlove.x && x <= RGlove.x + RGlove.width && y >= RGlove.y && y <= RGlove.y + RGlove.height ||
                            x >= LGlove.x && x <= LGlove.x + LGlove.width && y >= LGlove.y && y <= LGlove.y + LGlove.height) {
                            playerStam = playerStam - punchCost;
                            dodamage = false;
                            soundPool.play(gloveHit, 1,1,0,0,1);
                    }
                    else if (x >= face.x && x <= face.x + face.width && y >= face.y && y <= face.y + face.height ||
                            x >= character.x && x <= character.x + character.width && y >= character.y && y <= character.y + character.height){
                        dodamage = true;
                        if(playerStam <= punchCost){
                            canPunch = false;
                            Toast.makeText(getApplicationContext(), "No Stamina Chill Out!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            canPunch = true;
                        }
                        if(canPunch) {
                            damageEnemy();
                            punched = true;
                            playerStam = playerStam - punchCost;
                            playerStamPercentage = playerStam / playerMaxStam;
                            Random soundNum = new Random();
                            int randNum = soundNum.nextInt(4) + 1;

                            switch (randNum) {
                                case 1:
                                    soundPool.play(realPunch, 1, 1, 0, 0, 1);
                                    break;
                                case 2:
                                    soundPool.play(PUNCH, 1, 1, 0, 0, 1);
                                    break;
                                case 3:
                                    soundPool.play(jabPunch, 1, 1, 0, 0, 1);
                                    break;
                                case 4:
                                    soundPool.play(Hit_Hurt, 1, 1, 0, 0, 1);
                                    break;
                                case 5:
                                    soundPool.play(Hit_Hurt3, 1, 1, 0, 0, 1);
                                    break;
                                case 6:
                                    soundPool.play(Hit_Hurt4, 1, 1, 0, 0, 1);
                                    break;
                                case 7:
                                    soundPool.play(Hit_Hurt5, 1, 1, 0, 0, 1);
                                    break;
                            }
                        }
                    }

                    if (x >= leftDod.x && x <= leftDod.x + leftDod.width && y >= leftDod.y && y <= leftDod.y + leftDod.height ||
                            x >= rightDod.x && x <= rightDod.x + rightDod.width && y >= rightDod.y && y <= rightDod.y + rightDod.height){
                        dodge = true;
                        soundPool.play(swoosh,1,1,0,0,1);
                        Toast.makeText(getApplicationContext(),"DODGE!", Toast.LENGTH_LONG).show();
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    break;
            }

            return false;
        }


        

        private void updateLogic() {
            levelUp();

            Face.srcX = Enemy.hurt * Face.width;

            if(fightOver){
                waitTimer += deltaTime;
                if(waitTimer > waitTime){
                    running = false;
                }
                return;
            }

            if(punched) {
                punchTimer += deltaTime;
                if (punchTimer > punchTime && !punchanim) {
                    damagePlayer();

                    playerHealthPercentage = playerHealth / playerMaxHealth;
                    punchTimer = 0;
                }
                else {
                    enemypunch = false;
                }

            }
            if (dodge) {
                dodgetimer += deltaTime;
                if (dodgetimer >= 500){
                    dodge = false;
                    dodgetimer = 0;
                }
            }

            stamTimer += deltaTime;
            if(stamTimer > stamTime){
                if(playerStam != playerMaxStam)
                playerStam += playerStamRegen;
                playerStamPercentage = playerStam / playerMaxStam;
                stamTimer = 0;
            }
            LGlove.y += lvy;
            RGlove.y += rvy;

            if(LGlove.y > screenHeight - LGlove.height){

                lvy = lvy * -1;
                LGlove.y += lvy;
            }else if (LGlove.y < screenHeight / 2){

                lvy = lvy * -1;
                LGlove.y += lvy;
            }
            if(RGlove.y > screenHeight - RGlove.height) {
                rvy = rvy * -1;
                RGlove.y += rvy;
            }else if (RGlove.y < screenHeight / 2){

                rvy = rvy * -1;
                RGlove.y += rvy;
            }

            jeff.update(deltaTime);
        }

        private void drawCanvas(){
            if(holder.getSurface().isValid()){
                canvas = holder.lockCanvas();
                if (punchanim) {

                    if (scaleUp) {
                        scale += 0.2f;
                    }
                    else {
                        scale -= 0.2f;
                    }

                    if (scale >= 2.0f) {
                        scaleUp = false;
                    }
                    else if (scale <= 1.0f) {
                        scale = 1.0f;
                        scaleUp = true;
                        punchanim = false;
                    }
                }

                background.draw(canvas);
                character.draw(canvas);
                face.draw(canvas);

                if (punchanim && gloveNum == 1) {
                    canvas.save();
                    canvas.translate(RGlove.x + (RGlove.width / 2), RGlove.y + (RGlove.height / 2));//(rightGlove.x + rightGlove.width/2,rightGlove.y + rightGlove.height/2);
                    canvas.scale(scale, scale);
                    RGlove.draw(canvas, true);
                    canvas.restore();
                }
                else {
                    RGlove.draw(canvas, false);
                }
                if (punchanim && gloveNum == 2){
                    canvas.save();
                    canvas.translate(LGlove.x + (LGlove.width / 2), LGlove.y + (LGlove.height / 2));//(rightGlove.x + rightGlove.width/2,rightGlove.y + rightGlove.height/2);
                    canvas.scale(scale, scale);
                    LGlove.draw(canvas, true);
                    canvas.restore();
                }
                else {

                    LGlove.draw(canvas, false);
                }
                exit.draw(canvas);
                leftDod.draw(canvas);
                rightDod.draw(canvas);



                if(Enemy.facenum != 6) {
                    jeff.draw(canvas);
                }
                /*if(Enemy.facenum == 6){
                    MainActivity.player.stop();
                    MainActivity.player = MediaPlayer.create(context, R.raw.takeonme);
                    MainActivity.player.isLooping();
                    MainActivity.player.start();

                }*/   // test to change music for boss fight

                paint.setColor(Color.RED);
                paint.setTextSize(30);
                paint.setFakeBoldText(true);
                paint.setColor(Color.BLACK);
                canvas.drawRect(screenWidth - 250,(screenHeight / 4),screenWidth,400,paint);//player black bar   need to play around with values to adjust position
                canvas.drawRect(0,(screenHeight / 4),250,400,paint);//enemy black bar
                canvas.drawRect(screenWidth - 50, screenHeight, screenWidth,screenHeight,paint);//player black stam bar

                paint.setColor(Color.RED);
                canvas.drawRect(0,(screenHeight / 4),(int)(250f * enemyPercentage),400,paint);//enemy red bar
                paint.setColor(Color.GREEN);
                canvas.drawRect(screenWidth - (int)(250f * playerHealthPercentage),(screenHeight / 4),screenWidth,400,paint);//player red bar
                paint.setColor(Color.YELLOW);
                canvas.drawRect(screenWidth - (int)(250f * playerStamPercentage),(screenHeight / 4) + 40, screenWidth,480,paint);//player stambar
                paint.setColor(Color.WHITE);
                canvas.drawText(currentEnemy.enemyName,10,(screenHeight / 4),paint);
                canvas.drawText("Player Health",screenWidth - 250,(screenHeight / 4),paint);//paint.setTextSize(45);

                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void controlFPS() {
            long timeThisFrame = (System.nanoTime() / 1000000 - lastFrameTime);
            long timeToSleep = 15 - timeThisFrame;
            deltaTime = 0;

            if (timeThisFrame > 0){
                fps = (int) (1000/ timeThisFrame);
                deltaTime = timeThisFrame;
            }

            if (timeToSleep > 0){
                try{
                    thread.sleep(timeToSleep);
                }catch(InterruptedException e){

                }
            }
            lastFrameTime = System.nanoTime() / 1000000;
        }

        @Override
        public void run(){
            Looper.prepare();
            //Looper.loop();
            while (running){

                updateLogic();
                drawCanvas();
                controlFPS();
            }
            if(fightOver){
                running = false;
                MainActivity.player.stop();
                Intent finish = new Intent(GameActivity.this,storePage.class);
                startActivity(finish);
                finish();
            }
        }



        public void resume() {
            thread = new Thread(this);
            thread.start();

        }

        public void pause(){
            try {
                thread.join();
            }
            catch (InterruptedException e){

            }
        }
    }
}

