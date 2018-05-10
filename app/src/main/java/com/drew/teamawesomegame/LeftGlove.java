package com.drew.teamawesomegame;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class LeftGlove {
    int x = 0;
    int y = 0;
    int width;
    int height;

    //TODO fix sizing and such

    private final Bitmap lGlove;
    private int srcX = 0;
    private int srcY = 0;

    public LeftGlove(Bitmap lGlove) {
        this.lGlove = lGlove;

        width = lGlove.getWidth();
        height = lGlove.getHeight();
    }

    public void draw(Canvas canvas) {
        Rect srcRect = new Rect(srcX,srcY,srcX+width,srcY+height);
        Rect dstRect = new Rect(x,y,x+width,y+height);

        canvas.drawBitmap(lGlove, srcRect, dstRect, null);
    }
}
