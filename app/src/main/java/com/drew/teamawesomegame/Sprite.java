package com.drew.teamawesomegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
    int x = 0;
    int y = 0;
    int width;
    int height;

    int sheet_rows = 4;
    int sheet_cols = 3;
    //TODO fix sizing and such

    private final Bitmap bmp;
    private int srcX = 0;
    private int srcY = 0;
    private int currentFrame = 7;

    public  Sprite(Bitmap bmp) {
        this.bmp = bmp;

        width = bmp.getWidth() / sheet_cols;
        height = bmp.getHeight() / sheet_rows;

        srcX = currentFrame % sheet_cols * width;
        srcY = currentFrame / sheet_cols * height;
    }

    public void draw(Canvas canvas) {
        Rect srcRect = new Rect(srcX,srcY,srcX+width,srcY+height);
        Rect dstRect = new Rect(x,y,x+width,y+height);

        canvas.drawBitmap(bmp, srcRect, dstRect, null);
    }
}
