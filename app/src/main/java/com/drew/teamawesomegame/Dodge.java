package com.drew.teamawesomegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Dodge {
    int x = 0;
    int y = 0;
    int width;
    int height;

    private final Bitmap dod;


    public Dodge(Bitmap dod) {
        this.dod = dod;

        width = dod.getWidth();
        height = dod.getHeight();

    }

    public void draw(Canvas canvas) {
        Rect srcRect = new Rect(0,0,width,height);
        Rect dstRect = new Rect(x,y,x+width,y+height);

        canvas.drawBitmap(dod, srcRect, dstRect, null);
    }
}
