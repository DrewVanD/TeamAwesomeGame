package com.drew.teamawesomegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class RightDodge {
    int x = 0;
    int y = 0;
    int width;
    int height;


    //TODO fix sizing and such

    private final Bitmap dodri;


    public RightDodge(Bitmap dodri) {
        this.dodri = dodri;

        width = dodri.getWidth();
        height = dodri.getHeight();

    }

    public void draw(Canvas canvas) {
        Rect srcRect = new Rect(0,0,width,height);
        Rect dstRect = new Rect(x,y,x+width,y+height);

        canvas.drawBitmap(dodri, srcRect, dstRect, null);
    }
}
