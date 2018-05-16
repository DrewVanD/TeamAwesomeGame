package com.drew.teamawesomegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class button {
    int x = 0;
    int y = 0;
    int width;
    int height;

    private final Bitmap wussoutButton;
    private final Bitmap dodgeLButton;
    private final Bitmap dodgeRButton;
    private int srcX = 0;
    private int srcY = 0;


    public button(int x, int y, int width, int height, Bitmap wussoutButton, Bitmap dodgeLButton, Bitmap dodgeRButton) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.wussoutButton = wussoutButton;
        this.dodgeLButton = dodgeLButton;
        this.dodgeRButton = dodgeRButton;
    }

    public void draw(Canvas canvas) {
        Rect srcRect = new Rect(srcX,srcY,srcX+width,srcY+height);
        Rect dstRect = new Rect(x,y,x+width,y+height);

        canvas.drawBitmap(wussoutButton, srcRect, dstRect, null);
        canvas.drawBitmap(dodgeLButton, srcRect, dstRect, null);
        canvas.drawBitmap(dodgeRButton, srcRect, dstRect, null);
    }
}
