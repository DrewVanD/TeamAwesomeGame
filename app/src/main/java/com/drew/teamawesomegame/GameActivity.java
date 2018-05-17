package com.drew.teamawesomegame;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    //TODO determine how to grab canvas for the original surface view
    Canvas canvas;
    SpriteView spriteView;

    private SoundPool soundPool;
    int realPunch = -1;
    int PUNCH = -1;
    int jabPunch = -1;
    int Hit_Hurt = -1;
    int Hit_Hurt3 = -1;
    int Hit_Hurt4 = -1;
    int Hit_Hurt5 = -1;
    int Hit_Hurt6 = -1;
    Enemy currentEnemy;


    private  SoundPool backgroundMusic;
    int eyeoftiger8bit = -1;

    @Override
    protected void onPause() {
        super.onPause();
        spriteView.pause();
        backgroundMusic.pause(eyeoftiger8bit);


    }

    @Override
    protected void onResume() {
        super.onResume();
        spriteView.resume();
        backgroundMusic.play(1,2,2,0,1,eyeoftiger8bit);

/*
        soundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,0);
        try {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("eyeoftiger8bit.mp3");
            eyeoftiger8bit = soundPool.load(descriptor, 0);
        }catch (IOException e){

        }*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        Bundle bundle = getIntent().getBundleExtra("BUNDLE");

        /*bundle.putInt("Health",200);
        bundle.putInt("MaxHealth",200);
        bundle.putInt("DamagePerSwing",12);
        bundle.putInt("CoinReward",150);
        bundle.putInt("ExpReward",75);
        bundle.putInt("TimeBetweenSwings",4);
        bundle.putInt("FaceDamage",0);
        bundle.putInt("FaceNum",1);
        bundle.putString("EnemyName","Alexx 'Quanterooni' Quan");*/

        int health = bundle.getInt("Health",0);
        int maxHealth = bundle.getInt("MaxHealth", 0);
        int damagePerSwing = bundle.getInt("damagePerSwing", 0);
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
            descriptor = assetManager.openFd("Hit_Hurt6.wav");
            Hit_Hurt6 = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("eyeoftiger8bit.mp3");
            eyeoftiger8bit = soundPool.load(descriptor,0);
        } catch (IOException e) {
        }
        backgroundMusic = new SoundPool(1,AudioManager.STREAM_MUSIC,0);


    }


    class SpriteView extends SurfaceView implements  Runnable {

        Thread thread = null;
        final SurfaceHolder holder;
        final Paint paint;

        Bitmap bmp;
        Bitmap bit;
        Bitmap bim;
        Bitmap rGlove;
        Bitmap lGlove;
        Bitmap jiff;
        jeffBartender jeff;
        Body character;
        Face face;
        LeftGlove leftGlove;
        RightGlove rightGlove;
        //sprite test for background
        BackGround background;
        //Timers
        long stamTimer = 0;
        long stamTime = 1000;
        long punchTimer = 0;
        long punchTime = currentEnemy.timeBetweenSwings * 1000;


        float playerHealth = playerStats.playerHealth;
        float playerMaxHealth = playerStats.playerMaxHealth;
        float playerStam = playerStats.playerStam;
        float playerMaxStam = playerStats.playerMaxStam;
        float playerStamRegen = playerStats.playerStamRegen;
        int punchCost = playerStats.PunchCost;
        boolean canPunch = true;
        int baseDmg = 10;
        float enemyPercentage = Enemy.health / Enemy.maxHealth;
        float playerHealthPercentage = playerHealth / playerMaxHealth;
        float playerStamPercentage = playerStam / playerMaxStam;
        Display display;
        int screenWidth;
        int screenHeight;
        long lastFrameTime;
        long deltaTime;
        int fps;
        int rvy = 4;
        int lvy = 4;

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

            //Canvas canvas = new Canvas(bit.copy(Bitmap.Config.ARGB_8888, true));
            background = new BackGround(bit);

            background.x = 0;
            background.y = 0;

            character = new Body(bmp);
            character.x = (screenWidth / 2) - (character.width / 2);
            character.y = (background.height / 2) + (character.height / 2);

            face = new Face(bim);
            face.x = (screenWidth / 2) - (face.width / 2) + (screenWidth / 100);
            face.y = background.height / 2;

            rightGlove = new RightGlove(rGlove);
            rightGlove.x = (screenWidth / 5) * 3;
            rightGlove.y = (background.height / 2) + (rightGlove.height) - 100;

            leftGlove = new LeftGlove(lGlove);
            leftGlove.x =screenWidth / 13;
            leftGlove.y = (background.height / 2) + (leftGlove.height) - 100;

            jeff = new jeffBartender(jiff);
            jeff.addAnimation("bar",0,3,7,110,96,true);
            jeff.setAnimation("bar");

            jeff.x = (screenWidth / 5) * 3;
            jeff.y = screenHeight / 14;



        }

        public void damageEnemy(){

            if (Enemy.health <= Enemy.maxHealth / 2){
                currentEnemy.hurt = 1;//not changing face yet
            }
            if(Enemy.health <= 0)
            {
                exp += currentEnemy.expReward;
                Toast.makeText(getApplicationContext(),currentEnemy.enemyName + " Dead"
                                                           + "\nCoin Reward: " + currentEnemy.coinReward
                                                            + "\nExp Reward: " + currentEnemy.expReward, Toast.LENGTH_LONG).show();
                levelUp();
            }
            Enemy.health -= baseDmg;
            enemyPercentage = Enemy.health / Enemy.maxHealth;



        }

        public void levelUp() {
            if (exp >= expincrement){
                playerStats.playerMaxHealth += 10;
                playerStats.playerMaxStam += 10;
                playerStats.baseDamage += 1;
                exp = 0;
                playerlvl ++;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    if(playerStam <= punchCost){
                        canPunch = false;
                    }
                    else{
                        canPunch = true;
                    }
                    if(canPunch) {
                        damageEnemy();
                        playerStam = playerStam - punchCost;
                        playerStamPercentage = playerStam / playerMaxStam;
                        Random soundNum = new Random();
                        int randNum = soundNum.nextInt(4) + 1;

                        switch (randNum) {
                            case 1:
                                soundPool.play(realPunch, 1, 1, 0, 0 ,1);
                                break;
                            case 2:
                                soundPool.play(PUNCH, 1, 1, 0, 0 ,1);
                                break;
                            case 3:
                                soundPool.play(jabPunch, 1, 1, 0, 0 ,1);
                                break;
                            case 4:
                                soundPool.play(Hit_Hurt, 1, 1, 0, 0 ,1);
                                break;
                            case 5:
                                soundPool.play(Hit_Hurt3, 1, 1, 0, 0 ,1);
                                break;
                            case 6:
                                soundPool.play(Hit_Hurt4, 1, 1, 0, 0 ,1);
                                break;
                            case 7:
                                soundPool.play(Hit_Hurt5, 1, 1, 0, 0 ,1);
                                break;
                            case 8:
                                soundPool.play(Hit_Hurt6, 1, 1, 0, 0 ,1);
                                break;
                        }
                        break;

                    }
                case MotionEvent.ACTION_UP:

                    break;
                    }


            return false;
        }

        

        private void updateLogic() {
            punchTimer += deltaTime;
            if(punchTimer > punchTime){
                playerHealth -= currentEnemy.damage;
                playerHealthPercentage = playerHealth / playerMaxHealth;
                punchTimer = 0;
            }


            stamTimer += deltaTime;
            if(stamTimer > stamTime){
                if(playerStam != playerMaxStam)
                playerStam += playerStamRegen;
                playerStamPercentage = playerStam / playerMaxStam;
                stamTimer = 0;
            }
            leftGlove.y += lvy;
            rightGlove.y += rvy;

            if(leftGlove.y > screenHeight - leftGlove.height){

                lvy = lvy * -1;
                leftGlove.y += lvy;
            }else if (leftGlove.y < screenHeight / 2){

                lvy = lvy * -1;
                leftGlove.y += lvy;
            }
            if(rightGlove.y > screenHeight - rightGlove.height) {
                rvy = rvy * -1;
                rightGlove.y += rvy;
            }else if (rightGlove.y < screenHeight / 2){

                rvy = rvy * -1;
                rightGlove.y += rvy;
            }

            jeff.update(deltaTime);
        }

        private void drawCanvas(){
            if(holder.getSurface().isValid()){
                canvas = holder.lockCanvas();

                background.draw(canvas);
                character.draw(canvas);
                face.draw(canvas);
                rightGlove.draw(canvas);
                leftGlove.draw(canvas);
                if(Enemy.facenum != 6) {
                    jeff.draw(canvas);
                }

                paint.setColor(Color.RED);
                paint.setTextSize(30);
                paint.setFakeBoldText(true);
                paint.setColor(Color.BLACK);
                canvas.drawRect(canvas.getWidth() - 250,(canvas.getHeight() / 4),canvas.getWidth(),400,paint);//player black bar
                canvas.drawRect(0,(canvas.getHeight() / 4),250,400,paint);//enemy black bar
                canvas.drawRect(canvas.getWidth() - 50, canvas.getHeight(), canvas.getWidth(),canvas.getHeight(),paint);//pplayer black stam bar

                paint.setColor(Color.RED);
                canvas.drawRect(0,(canvas.getHeight() / 4),(int)(250f * enemyPercentage),400,paint);//enemy red bar
                paint.setColor(Color.GREEN);
                canvas.drawRect(canvas.getWidth() - 250,(canvas.getHeight() / 4),(int)(canvas.getWidth() * playerHealthPercentage),400,paint);//player red bar
                paint.setColor(Color.YELLOW);
                canvas.drawRect(canvas.getWidth() - 50, canvas.getHeight() - (int)(250f * playerStamPercentage), canvas.getWidth(),canvas.getHeight(),paint);//player stambar
                paint.setColor(Color.WHITE);
                canvas.drawText(currentEnemy.enemyName,10,(canvas.getHeight() / 4),paint);
                canvas.drawText("Player Health",canvas.getWidth() - 250,(canvas.getHeight() / 4),paint);//paint.setTextSize(45);
                //canvas.drawText("FPS: " + fps,10,40, paint);
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
            while (true){
                updateLogic();
                drawCanvas();
                controlFPS();
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

