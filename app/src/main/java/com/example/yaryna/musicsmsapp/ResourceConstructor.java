package com.example.yaryna.musicsmsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

/**
 * Created by 15038588
 */
public class ResourceConstructor {

    /**Quaver 1x
     * Crotchet  2x
     * Dotted crotchet 3x
     * Minim   4x
     * Dotted minim  6x
     * Semibreve  8x */
    private static ResourceConstructor instance;


    public static synchronized ResourceConstructor getInstance()
    {
        if (instance == null)
            instance = new ResourceConstructor();
        return instance;
    }

    public int getNoteImageResourceId(double line , int duration){
        int resourceID = 0;
        //BitmapFactory.decodeResource(getResources(), R.drawable.treble_clef);
        //stem up
        if(line > 3) {
            switch (duration){
                case 1:
                    resourceID =  R.drawable.quaver_stem_up;
                    break;
                case 2:resourceID =  R.drawable.crotchet_stem_up;
                    break;
                case 3:resourceID =  R.drawable.crotchet_dotted_stem_up;
                    break;
                case 4:resourceID =  R.drawable.minim_stem_up;
                    break;
                case 6:resourceID =  R.drawable.minim_dotted_stem_up;
                    break;
                case 8:resourceID =  R.drawable.semibreve;
                    break;

            }
        }
        //stem down
        else if (line <=3){
            switch (duration){
                case 1:
                    resourceID =  R.drawable.quaver_stem_down;
                    break;
                case 2:resourceID =  R.drawable.crotchet_stem_down;
                    break;
                case 3:resourceID =  R.drawable.crotchet_dotted_stem_down;
                    break;
                case 4:resourceID =  R.drawable.minim_stem_down;
                    break;
                case 6:resourceID =  R.drawable.minim_dotted_stem_down;
                    break;
                case 8:resourceID =  R.drawable.semibreve;
                    break;

            }
        }
        return resourceID;
    }

}
