package com.example.yaryna.musicsmsapp;

/**
 * Created by 15038588
 */

public class LinePositionConstructor {

    private static LinePositionConstructor instance;

    public static synchronized LinePositionConstructor getInstance()
    {
        if (instance == null)
            instance = new LinePositionConstructor();
        return instance;
    }


    /**Lines assignment is done in reverse -> the first line (E4 line)
     * is assigned to 5 and last line (E5 line )is assigned to 1*/
    public double getLine(String pitch){
        double result = 0;
        switch(pitch){
            case "Eb4":result = 5;
                break;
            case "E4":result = 5;
                break;
            case "Gb4":result = 4;
                break;
            case "G4": result = 4;
                break;
            case "Bb4":result = 3;
                break;
            case "B4":result = 3;
                break;
            case "Db5":result = 2;
                break;
            case "D5":result = 2;
                break;
            case "F5":result = 1;
                break;
            case "F4":result = 4.5;
                break;
            case "Fb4":result = 4.5;
                break;
            case "Ab4":result = 3.5;
                break;
            case "A4":result = 3.5;
                break;
            case "C5":result = 2.5;
                break;
            case "Eb5":result = 1.5;
                break;
            case "E5":result = 1.5;
                break;
            case"Cb5":result = 2.5;
                break;
            default:break;
        }
        return result;
    }


}
