package com.drew.teamawesomegame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
//TODO determine how to grab canvas for the original surface view
    Canvas canvas;
    SpriteView spriteView;

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

        /*Button buttonQuit = findViewById(R.id.buttonQuit);
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
        buttonPunch6.setOnClickListener(this);*/

        spriteView = new SpriteView(this);
        setContentView(spriteView);
    }

    /*public void damageEnemy(){
        if(Enemy.health >= 0) {
            Enemy.health -= playerStats.baseDamage;
        }
    }*/



    class SpriteView extends SurfaceView implements  Runnable {

        Thread thread = null;
        final SurfaceHolder holder;
        final Paint paint;

        Bitmap bmp;
        Bitmap bit;
        Bitmap bim;
        Bitmap rGlove;
        Bitmap lGlove;
        Body character;
        Face face;
        LeftGlove leftGlove;
        RightGlove rightGlove;
        //sprite test for background
        BackGround background;
        int percentage = Enemy.health / Enemy.maxHealth;
        int baseDmg = 10;

        long lastFrameTime;
        int fps;
        int rvy = 1;
        int lvy = 2;

        public SpriteView(Context context) {
            super(context);

            holder = getHolder();
            paint = new Paint();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.body2);
            bit = BitmapFactory.decodeResource(getResources(), R.drawable.ring2);
            bim = BitmapFactory.decodeResource(getResources(), R.drawable.faces);
            rGlove = BitmapFactory.decodeResource(getResources(), R.drawable.rglove);
            lGlove = BitmapFactory.decodeResource(getResources(), R.drawable.lglove);

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



        }

        public void damageEnemy(){
            Enemy.health -= baseDmg;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    damageEnemy();
                    break;
                case MotionEvent.ACTION_UP:

                    break;
            }

            return false;
        }

        public void updateHealthBars(){
            Rect healthBarBack = new Rect();
            Rect healthBarFront = new Rect();



        }

        private void updateLogic() {// Matts test moved gloves to top instead of bottom?? comments are originals

            if(leftGlove.y > background.height - leftGlove.height - 50){
                leftGlove.y += lvy;
                lvy = lvy * -1;
            }else if (leftGlove.y < background.height / 2){
                leftGlove.y += lvy;
                lvy = lvy * -1;
            }
            if(rightGlove.y > background.height - rightGlove.height - 50) {
                rightGlove.y += rvy;
                rvy = rvy * -1;
            }else if (rightGlove.y < background.height / 2){
                rightGlove.y += rvy;
                rightGlove.y = background.height / 2 + 10;
                rvy = rvy * -1;
            }

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
                int barWidth = 250 * percentage;
                background.draw(canvas);
                character.draw(canvas);
                face.draw(canvas);
                rightGlove.draw(canvas);
                leftGlove.draw(canvas);

                paint.setColor(Color.RED);
                paint.setTextSize(30);
                paint.setFakeBoldText(true);
                paint.setColor(Color.BLACK);
                canvas.drawRect(0,350,250,400,paint);
                canvas.drawText("Enemy Health",10,340,paint);
                paint.setColor(Color.RED);
                canvas.drawRect(0,350,barWidth,400,paint);
                //paint.setTextSize(45);
                //canvas.drawText("FPS: " + fps,10,40, paint);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void controlFPS() {
            long timeThisFrame = (System.nanoTime() / 1000000 - lastFrameTime);
            long timeToSleep = 15 - timeThisFrame;

            if (timeThisFrame > 0){
                fps = (int) (1000/ timeThisFrame);
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
