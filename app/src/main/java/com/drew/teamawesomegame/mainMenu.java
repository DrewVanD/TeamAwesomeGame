package com.drew.teamawesomegame;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class mainMenu extends AppCompatActivity implements View.OnClickListener {

    private SoundPool menuMusic;
    int eyeoftiger8bit = -1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_menu);

            Button playButton = findViewById(R.id.playButton);
            playButton.setOnClickListener(this);

            Button statsButton = findViewById(R.id.statsButton);
            statsButton.setOnClickListener(this);

            Button storeButton = findViewById(R.id.storeButton);
            storeButton.setOnClickListener(this);

            Button aboutButton = findViewById(R.id.aboutButton);
            aboutButton.setOnClickListener(this);
            Log.d("*****", "dasdsa");
            menuMusic = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            menuMusic.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    soundPool.play(sampleId, 1, 1, 1, -1, 1);
                    Log.d("*****", "play music");
                }
            });

            try {
                AssetManager assetManager = getAssets();
                AssetFileDescriptor descriptor;

                descriptor = assetManager.openFd("eyeoftiger8bit.ogg");
                eyeoftiger8bit = menuMusic.load(descriptor,0);

            } catch (IOException e) {

            }
        }



        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.playButton:
                    Intent i = new Intent(this, playGame.class);
                    menuMusic.stop(eyeoftiger8bit);
                    startActivity(i);
                    break;
                case R.id.statsButton:
                    Intent j = new Intent(this, playerStats.class);
                    menuMusic.stop(eyeoftiger8bit);
                    startActivity(j);
                    break;
                case R.id.storeButton:
                    Intent k = new Intent(this, storePage.class);
                    menuMusic.stop(eyeoftiger8bit);
                    startActivity(k);
                    break;
                case R.id.aboutButton:
                    Intent l = new Intent(this, aboutPage.class);
                    menuMusic.stop(eyeoftiger8bit);
                    startActivity(l);
                default:
                    break;

            }
        }

        public void resume() {
            menuMusic.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    soundPool.play(sampleId, 1, 1, 1, -1, 1);
                    Log.d("*****", "play music");
                }
            });

    }

     public void pause(){
         menuMusic.stop(eyeoftiger8bit);
    }
}
