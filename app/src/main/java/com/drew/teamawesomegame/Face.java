package com.drew.teamawesomegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Face {
    int x = 0;
    int y = 0;
    public static int width;
    int height;

    int sheet_rows = 7;
    int sheet_cols = 2;

    private final Bitmap bim;
    public static int srcX = 0;
    private int srcY = 0;

    public Face(Bitmap bim) {
        this.bim = bim;

        width = bim.getWidth() / sheet_cols;
        height = bim.getHeight() / sheet_rows;

        srcX = Enemy.hurt * width;
        srcY = Enemy.facenum * height;
    }

    public void draw(Canvas canvas) {
        Rect srcRect = new Rect(srcX,srcY,srcX+width,srcY+height);
        Rect dstRect = new Rect(x,y,x+width,y+height);

        canvas.drawBitmap(bim, srcRect, dstRect, null);
    }
}
