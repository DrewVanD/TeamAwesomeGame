package com.drew.teamawesomegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Exit {
    int x = 0;
    int y = 0;
    int width;
    int height;


    private final Bitmap ex;


    public Exit(Bitmap ex) {
        this.ex = ex;

        width = ex.getWidth();
        height = ex.getHeight();

    }

    public void draw(Canvas canvas) {
        Rect srcRect = new Rect(0,0,width,height);
        Rect dstRect = new Rect(x,y,x+width,y+height);

        canvas.drawBitmap(ex, srcRect, dstRect, null);
    }
}
