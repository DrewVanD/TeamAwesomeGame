package com.drew.teamawesomegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class jeffBartender {
        int x = 0;
        int y = 0;
        int width;
        int height;

        int sheet_rows = 1;
        int sheet_cols = 4;

        private final Bitmap jiff;
        private int srcX = 0;
        private int srcY = 0;

        public jeffBartender(Bitmap jiff) {
            this.jiff = jiff;

            width = jiff.getWidth() / sheet_cols;
            height = jiff.getHeight() / sheet_rows;
        }

        public void draw(Canvas canvas) {
            Rect srcRect = new Rect(srcX,srcY,srcX+width,srcY+height);
            Rect dstRect = new Rect(x,y,x+width,y+height);

            canvas.drawBitmap(jiff, srcRect, dstRect, null);
        }
}
