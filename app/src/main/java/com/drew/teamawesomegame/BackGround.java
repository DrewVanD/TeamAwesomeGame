package com.drew.teamawesomegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BackGround {
        int x = 0;
        int y = 0;
        int width;
        int height;


        private final Bitmap bit;
        private int srcX = 0;
        private int srcY = 0;

        public  BackGround(Bitmap bit) {
            this.bit = bit;

            width = bit.getWidth();
            height = bit.getHeight();

            srcX = 0;// width;
            srcY = 0;//height;
        }

        public void draw(Canvas canvas) {
            Rect srcRect = new Rect(srcX,srcY,srcX+width,srcY+height);
            Rect dstRect = new Rect(x,y,canvas.getWidth(),canvas.getHeight());

            canvas.drawBitmap(bit, srcRect, dstRect, null);
        }

}
