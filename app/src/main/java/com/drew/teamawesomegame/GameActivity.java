package com.drew.teamawesomegame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
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

        spriteView = new SpriteView(this);
        setContentView(spriteView);
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
    class SpriteView extends SurfaceView implements  Runnable {

        Thread thread = null;
        final SurfaceHolder holder;
        //final Paint paint;

        Bitmap bmp;
        Bitmap bit;
        Sprite character;
        //sprite test for background
        BackGround background;

        long lastFrameTime;
        int fps;

        public SpriteView(Context context) {
            super(context);

            holder = getHolder();
            //paint = new Paint();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.spritesheet);
            bit = BitmapFactory.decodeResource(getResources(), R.drawable.ring2);

            //Canvas canvas = new Canvas(bit.copy(Bitmap.Config.ARGB_8888, true));
            background = new BackGround(bit);

            background.x = 0;
            background.y = 0;

            character = new Sprite(bmp);

            character.x = 400;
            character.y = 800;//TODO : need to fix to make sure its in center and at bottom on all screens
        }

        private void updateLogic() {
            //character.y += 1;
        }

        private void drawCanvas(){
            if(holder.getSurface().isValid()){
                canvas = holder.lockCanvas();
                //canvas.drawColor(Color.TRANSPARENT);
                //canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),background);

                background.draw(canvas);
                character.draw(canvas);
                //paint.setColor(Color.argb(255,255,255,255));
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
