package com.example.yaryna.musicsmsapp;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by 15038588
 */
public class SoundMaker {

    private final int SAMPLE_RATE = 8000 ;
    private  byte[] audioBuffer;
    private  ArrayList<NoteInstance> notes;

    public SoundMaker(ArrayList<NoteInstance> noteInstances){
        audioBuffer = null;
        this.notes = noteInstances;
        constructAudioBufferForMelody();
    }


    private void constructAudioBufferForMelody(){
        for(NoteInstance note :this.notes){
            //The purpose for this is to avoid sound jums between notes
            //NoteInstance emptynote = new NoteInstance("0XX");
            fillBufferForNote(note);
            //fillBufferForNote(emptynote);
        }
    }

    /**The fillBuffer() method creates a buffer with values
     * representing a sinusoidal wave of a given frequency*/
    private void fillBufferForNote(NoteInstance note) {
       double noteFrequency = note.getFrequency();
        //TODO : CHECK THE DURATION
        int noteDuration = note.getDuration();
        int sample_size = noteDuration*SAMPLE_RATE;
        byte[] noteAudioBuffer = new byte[2*sample_size];

        for (int i=0;i<noteAudioBuffer.length/2;i++) {
            double val = Math.sin(2 * Math.PI * i / (SAMPLE_RATE/noteFrequency));
            short normVal = (short) ((val*32767));
            noteAudioBuffer[2*i] = (byte) (normVal & 0x00ff);
            noteAudioBuffer[2*i+1] = (byte) ((normVal & 0xff00) >>> 8);
        }
        try {
            addToGlobalAudioBuffer(noteAudioBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Add new created buffer for one note to general notes audio buffer
    private void addToGlobalAudioBuffer(byte[] noteAudioBuffer) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        try {
            if(audioBuffer != null)
                outputStream.write(this.audioBuffer);
            outputStream.write(noteAudioBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        audioBuffer = null; // clear before assigning new values
        audioBuffer = outputStream.toByteArray();
    }


    /**The playBuffer() plays that buffer using an AudioTrack object,and the
     *  playNote() method uses the previous two methods to play a note*/
    private void playBuffer() {
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                SAMPLE_RATE, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, audioBuffer.length,
                AudioTrack.MODE_STATIC);
        audioTrack.write(audioBuffer, 0, audioBuffer.length);
        audioTrack.play();
    }

    public void playNotes() {
       new BackgroundPlay().execute();
    }

    private class BackgroundPlay extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            playBuffer();
            return null;
        }
    }
}
