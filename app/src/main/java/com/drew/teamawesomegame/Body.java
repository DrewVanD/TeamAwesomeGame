package com.drew.teamawesomegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Body {
    int x = 0;
    int y = 0;
    int width;
    int height;
    int scalex;
    int scaley;

    int sheet_rows = 3;
    int sheet_cols = 1;

    private final Bitmap bmp;
    private int srcX = 0;
    private int srcY = 0;
    public static int bodyNumber = 0;

    public  Body(Bitmap bmp) {
        this.bmp = bmp;

        width = bmp.getWidth() / sheet_cols;
        height = bmp.getHeight() / sheet_rows;

        srcX = 0;
        srcY = bodyNumber / sheet_cols * height;

        scalex = width * (3 / 2);
        scaley = height * (3 / 2);
    }

    public void draw(Canvas canvas) {
        Rect srcRect = new Rect(srcX,srcY,srcX+width,srcY+height);
        Rect dstRect = new Rect(x,y,x+width,y+height);

        canvas.drawBitmap(bmp, srcRect, dstRect, null);
    }
}
