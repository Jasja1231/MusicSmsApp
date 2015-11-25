package com.example.yaryna.musicsmsapp;

import android.provider.CalendarContract;

/**
 * Created by Yaryna on 20/11/2015.
 */
public class NoteInstance {

    //The position of a note on the stave indicates its pitch
    private String pitch = "";
    private boolean hasFlat = false;
    private int duration = 0;
    private double frequency;
    double line = 0;
    int resourceID = 0;


    public double getFrequency() {
        return this.frequency;
    }

    public int getDuration() {
        return this.duration;
    }

    public double getLine() {
        return this.line;
    }

    public int getResourceID() {
        return this.resourceID;
    }

    public boolean hasFlat(){return this.hasFlat;}

    public NoteInstance(String noteString){
        //Check if has flat
        if(noteString.length() == 4)
            hasFlat = true;

        //split noteString into separate characters
        String durationAsString = String.valueOf(noteString.charAt(0));
        this.duration = Integer.parseInt(durationAsString);
        this.pitch = noteString.substring(1, noteString.length());

        setLine();
        setFrequency();
        setResourceID();
    }


    private void setLine(){
        this.line = new LinePositionConstructor().getInstance().getLine(this.pitch);
        if(this.line == 0)
            System.out.println("CUSTOM MESSAGE ::LINE FOR NOTE " + this.pitch + " IS ZERO!");
    }

    private void setFrequency(){
        this.frequency = new SoundConstructor().getInstance().getSpecificSoundFrequency(this.pitch);
        if(frequency == 0)
            System.out.println("CUSTOM MESSAGE :: FREAQUENCY OF NOTE " + this.pitch + "IS ZERO!");
    }

    private void setResourceID(){
        this.resourceID = new ResourceConstructor().getInstance().getNoteImageResourceId(this.line, this.duration);
        if(frequency == 0)
            System.out.println("CUSTOM MESSAGE :: Resource Id OF NOTE " + this.pitch + "IS ZERO!");
    }
}
