package com.drew.teamawesomegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class RightGlove {
    int x = 0;
    int y = 0;
    int width;
    int height;

    //TODO fix sizing and such

    private final Bitmap rGlove;
    private int srcX = 0;
    private int srcY = 0;

    public RightGlove(Bitmap rGlove) {
        this.rGlove = rGlove;

        width = rGlove.getWidth();
        height = rGlove.getHeight();
    }

    public void draw(Canvas canvas) {
        Rect srcRect = new Rect(srcX,srcY,srcX+width,srcY+height);
        Rect dstRect = new Rect(x,y,x+width,y+height);

        canvas.drawBitmap(rGlove, srcRect, dstRect, null);
    }
}
