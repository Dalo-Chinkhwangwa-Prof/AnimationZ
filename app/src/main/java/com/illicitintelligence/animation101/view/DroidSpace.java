package com.illicitintelligence.animation101.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.illicitintelligence.animation101.R;

public class DroidSpace extends View {

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("pause_game_event"))
                gamePaused = !gamePaused;
        }
    };

    private TypedArray imageArray;

    private Bitmap gazooObject;
    private Paint paintObject;
    private Boolean gamePaused = false;

    private final float SPEED = 5f;

    private float gazooSpeedY = SPEED;
    private float gazooSpeedX = SPEED;

    float xPosition = 0f;
    float yPosition = 0f;

    public DroidSpace(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        gazooObject = BitmapFactory.decodeResource(getResources(),
                R.drawable.gazoo);

        int newXSize = gazooObject.getWidth() / 3;
        int newYSize = gazooObject.getHeight() / 3;

        gazooObject = Bitmap.createScaledBitmap(gazooObject, newXSize, newYSize, false);

        paintObject = new Paint();

        context.registerReceiver(broadcastReceiver, new IntentFilter("pause_game_event"));

        imageArray = getResources().obtainTypedArray(R.array.my_loader_images);
    }

    int index = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        gazooObject = BitmapFactory.decodeResource(
                getResources(),
                imageArray.getResourceId(index, R.drawable.preloader_9_00000));
        gazooObject = Bitmap.createScaledBitmap(gazooObject, (gazooObject.getWidth() * 2), (gazooObject.getHeight() * 2), false);
        index++;

        if (index == imageArray.length() - 1)
            index = 0;

        //Set background color to black
//        canvas.drawColor(Color.BLACK);
//        if(!gamePaused) {
//            if (yPosition > (getHeight() - gazooObject.getHeight()) || yPosition < 0)
//                gazooSpeedY *= -1;
//
//            if (xPosition > (getWidth() - gazooObject.getWidth()) || xPosition < 0)
//                gazooSpeedX *= -1;
//
//
//            yPosition += gazooSpeedY;
//            xPosition += gazooSpeedX;
//
//        }
        canvas.drawBitmap(gazooObject, 150, 150, paintObject);
        invalidate();
    }
}
