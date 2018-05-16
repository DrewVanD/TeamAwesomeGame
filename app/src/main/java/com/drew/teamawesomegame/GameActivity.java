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
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    int Punch1 = -1;
    int Punch2 = -1;
    int Punch3 = -1;
    int Punch4 = -1;
    int Punch5 = -1;
    int Punch6 = -1;
    int Punch7 = -1;
    int Punch8 = -1;

    @Override
    protected void onPause() {
        super.onPause();
        spriteView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        spriteView.resume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        try {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("Punch1.wav");
            Punch1 = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Punch2.wav");
            Punch2 = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Punch3.wav");
            Punch3 = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Punch4.wav");
            Punch4 = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Punch5.wav");
            Punch5 = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Punch6.wav");
            Punch6 = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Punch7.wav");
            Punch7 = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("Punch8.wav");
            Punch8 = soundPool.load(descriptor, 0);
        }catch (IOException e){
            //TODO:Handle IO Exception
            }
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
        UI wussoutButton;
        UI dodgeRButton;
        UI dodgeLButton;

        jeffBartender jeff;
        Body character;
        Face face;
        LeftGlove leftGlove;
        RightGlove rightGlove;

        //sprite test for background
        BackGround background;
        Display display;
        Random r = new Random();
        int rand = (r.nextInt(3) - 1);
        int baseDmg = 10;
        int enemyHealth = 100;
        int enemyMaxHealth = 100;
        int percentage = enemyHealth / enemyMaxHealth;
        int screenHeight;
        int screenWidth;

        long lastFrameTime;
        long deltaTime;
        int fps;
        int rvy = 4;
        int lvy = 4;

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
            /*wussoutButton = BitmapFactory.decodeResource(getResources(),R.drawable.wussoutbutton);
            dodgeRButton = BitmapFactory.decodeResource(getResources(), R.drawable.dodgeright);
            dodgeLButton = BitmapFactory.decodeResource(getResources(),R.drawable.dodgeleft);*/

            //Canvas canvas = new Canvas(bit.copy(Bitmap.Config.ARGB_8888, true));
            background = new BackGround(bit);

            background.x = 0;
            background.y = 0;

            character = new Body(bmp);
            character.x = (background.width / 2) - (character.width / 2) + 60;
            character.y = (background.height / 2) + (character.height / 2);

            face = new Face(bim);
            face.x = (background.width / 2) - (face.width / 2) + 60;
            face.y = background.height / 2;

            rightGlove = new RightGlove(rGlove);
            rightGlove.x = (background.width / 2) + (rightGlove.width) - 100;
            rightGlove.y = (background.height / 2) + (rightGlove.height) - 100;

            leftGlove = new LeftGlove(lGlove);
            leftGlove.x =(background.width / 2) - (leftGlove.width - 20) - 85;
            leftGlove.y = (background.height / 2) + (leftGlove.height) - 100;

            jeff = new jeffBartender(jiff);
            jeff.addAnimation("bar",0,3,7,110,96,true);
            jeff.setAnimation("bar");

            jeff.x = (background.width / 2) + 100;
            jeff.y = 120;





        }

        public void damageEnemy(){
            if (enemyHealth <= enemyMaxHealth / 2){
                Enemy.hurt = 1;//not changing face yet
            }
            if(enemyHealth <= 0)
            {
                Toast.makeText(getApplicationContext(), "Enemy Dead", Toast.LENGTH_LONG).show();
            }
            enemyHealth -= baseDmg;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction() & MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    damageEnemy();
                    break;
                case MotionEvent.ACTION_UP:

                    break;
            }

            return false;
        }



        private void updateLogic() {
            leftGlove.y += lvy;
            rightGlove.y += rvy;
            if(leftGlove.y > screenHeight - leftGlove.height){
                lvy = lvy * -1;
                leftGlove.y += lvy;
            }else if(leftGlove.y < screenHeight / 2){

                lvy = lvy * -1;
                leftGlove.y += lvy;
            }
            if(rightGlove.y > screenHeight - rightGlove.height) {
                rvy = rvy * -1;
                rightGlove.y += rvy;
            }else if (rightGlove.y < screenHeight / 2)

        {

            rvy = rvy * -1;
            rightGlove.y += rvy;
        }
            jeff.update(deltaTime);

            /*if(leftGlove.y < background.height / 2){
                leftGlove.y += lvy;
            }
            if(rightGlove.y < background.height / 2){
                rightGlove.y += lvy;
            }*/


        }

        private void drawCanvas(){
            if(holder.getSurface().isValid()){
                canvas = holder.lockCanvas();
                //canvas.drawColor(Color.TRANSPARENT);
                //canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),background);

                background.draw(canvas);
                character.draw(canvas);
                face.draw(canvas);
                rightGlove.draw(canvas);
                leftGlove.draw(canvas);
                jeff.draw(canvas);

                paint.setColor(Color.RED);
                paint.setTextSize(30);
                paint.setFakeBoldText(true);
                paint.setColor(Color.BLACK);
                canvas.drawRect(canvas.getWidth() - 250,(canvas.getHeight() / 4),canvas.getWidth(),400,paint);//player black bar
                canvas.drawRect(0,(canvas.getHeight() / 4),250,400,paint);//enemy black bar
                canvas.drawRect(canvas.getWidth() - 50, canvas.getHeight(), canvas.getWidth(),canvas.getHeight(),paint);//pplayer black stam bar

                paint.setColor(Color.RED);
                canvas.drawRect(0,(canvas.getHeight() / 4),250 * percentage,400,paint);//enemy red bar
                paint.setColor(Color.GREEN);
                canvas.drawRect(canvas.getWidth() - 250,(canvas.getHeight() / 4),canvas.getWidth(),400,paint);//player red bar
                paint.setColor(Color.YELLOW);
                canvas.drawRect(canvas.getWidth() - 50, canvas.getHeight() - 250, canvas.getWidth(),canvas.getHeight(),paint);//player stambar
                paint.setColor(Color.WHITE);
                canvas.drawText("Enemy Health",10,(canvas.getHeight() / 4),paint);
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
