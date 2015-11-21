package com.example.yaryna.musicsmsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Yaryna on 21/11/2015.
 */
public class NotesView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    final int LINE_COUNT = 5;
    final int MARGIN = 80;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        drawParalelLines(canvas);

        //insert
        Bitmap b= BitmapFactory.decodeResource(getResources(), R.drawable.treble_clef);
        canvas.drawBitmap(b, 0, 0, paint);

    }


    /**Draws paralel lines with predefined margin distance*/
    private void drawParalelLines(Canvas canvas){
       for(int i=1; i <= LINE_COUNT; i++){
           int y_coordinate = MARGIN * i;
           canvas.drawLine( 0,  y_coordinate ,  getWidth(), y_coordinate, paint);
        }
    }

    public NotesView(Context context) {
        super(context);
    }

    public NotesView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NotesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
