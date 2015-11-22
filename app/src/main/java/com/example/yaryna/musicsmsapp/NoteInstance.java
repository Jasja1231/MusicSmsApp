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


    public double getFrequency(){return this.frequency;}
    public int getDuration() {return this.duration;}

    public NoteInstance(String noteString){
        //TODO: after testing parsing works fine make this code shorter(1 if statement)
        //Check if has flat
        if(noteString.length() == 4)
            hasFlat = true;
        else if(noteString.length() == 3)
                hasFlat = false;

        //split noteString into separate characters
        String durationAsString = String.valueOf(noteString.charAt(0));
        this.duration = Integer.parseInt(durationAsString);
        this.pitch = noteString.substring(1, noteString.length());

        setFrequency();
    }


    private void setFrequency(){
        this.frequency = new SoundConstructor().getSpecificSoundFrequency(this.pitch);
        if(frequency == 0)
            System.out.println("CUSTOM ERROR :: FREAQUENCE OF NOTE" + this.pitch + "IS ZERO!!!!!!!!!");
    }





}
