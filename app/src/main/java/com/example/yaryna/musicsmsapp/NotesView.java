package com.example.yaryna.musicsmsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by 15038588
 */
public class NotesView extends View {
    private Canvas canvas;

    public boolean finished = true;
    private ArrayList<NoteInstance> notes = null;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    final int LINE_COUNT = 5;
    final int MARGIN_BETWEEN_LINES = 80;

    final Bitmap trebleClefBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.treble_clef);
    /**
     * Rect(int left, int top, int right, int bottom)
     * Create a new rectangle with the specified coordinates.
     */
    final Rect trebleClefRect = new Rect(0,240,190,MARGIN_BETWEEN_LINES*(LINE_COUNT+1)+240);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        drawParallelLines(canvas);

        this.canvas = canvas;

        //Drawing treble clef on rectangle ares
        canvas.drawBitmap(trebleClefBitMap,null,trebleClefRect,paint);

        if(notes!=null)
            if(notes.isEmpty()!= true){
                    drawNotes(this.notes);
            }
        this.finished = true;
    }


    /**Draw notes from ArrayList<NoteInstance> notes on canvas.
     * @param notes is an ArrayList<NoteInstance> to be drawn*/
    private void drawNotes(ArrayList<NoteInstance> notes){
        paint.setStrokeWidth(5);
        double leftX = 200;

        for(NoteInstance note : notes ){
            double rightX = leftX + 100;
            double topY = 0 , bottomY = 0;

            double noteLine = note.getLine();

            if(noteLine > 3){
                bottomY = (noteLine+3)*MARGIN_BETWEEN_LINES + 30;
                topY =  bottomY - MARGIN_BETWEEN_LINES*3;
                if(note.getDuration() == 8){
                    topY = bottomY - 65;
                }
            }
            else if(noteLine <=3){
                topY = (noteLine+3)*80 - 30;
                bottomY =  topY + 80*3;
                if(note.getDuration() == 8){
                    bottomY = topY + 65;
                }
            }

            if(note.hasFlat() == true){
                final Bitmap flatBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flat);
                double topFlatY = 0;
                double bottomFlatY = 0;
                if(noteLine > 3){
                    topFlatY = bottomY - 80;
                    bottomFlatY = bottomY;
                }

                else if(noteLine <= 3){
                    bottomFlatY = topY + 60 ;
                    topFlatY = topY - 20 ;
                }

                rightX = leftX + 50;
                /**Rect(int left, int top, int right, int bottom)
                 Create a new rectangle with the specified coordinates.*/
                Rect noteRect = new Rect((int)leftX,(int)topFlatY,(int)rightX,(int)bottomFlatY);
                canvas.drawBitmap(flatBitmap,null,noteRect,paint);
                leftX = rightX + 15;
                rightX = leftX + 100;
            }


            if(note.getDuration() == 1 || note.getDuration() == 6 ){
                rightX = rightX + 20;
            }

            Bitmap noteBitmap = BitmapFactory.decodeResource(
                    getResources(), note.getResourceID());

            /**Rect(int left, int top, int right, int bottom)
             * Create a new rectangle with the specified coordinates.
             **/
            Rect noteRect = new Rect((int)leftX,(int)topY,(int)rightX,(int)bottomY);
            canvas.drawBitmap(noteBitmap,null,noteRect,paint);
            leftX = rightX + 10;
        }
    }

    /**Draws paralel lines with predefined margin distance
     * @param canvas  canvas for lines to be drawn on.
     **/
    private void drawParallelLines(Canvas canvas){
       for(int i=1; i <= LINE_COUNT; i++){
           int y_coordinate = MARGIN_BETWEEN_LINES * (i+3);
           canvas.drawLine( 0, y_coordinate , getWidth(), y_coordinate, paint);
        }
    }


    public void setNotesArray(ArrayList<NoteInstance> notesArray){
        this.finished = false;

        this.notes = null;
        this.notes = notesArray;
        invalidate();
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
