package com.example.yaryna.musicsmsapp;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Handler;


/**
 * Created by Yaryna on 21/11/2015.
 */
public class SoundMaker {
    private final int NOTE_DURATION = 2; //Note will last two seconds
    private final int SAMPLE_RATE = 8000;
    private final int SAMPLE_SIZE = NOTE_DURATION*SAMPLE_RATE;
    private final byte[] audioBuffer = new byte[2*SAMPLE_SIZE];
    private Handler handler = new Handler();


    /**The fillBuffer() method creates a buffer with values
     * representing a sinusoidal wave of a given frequency*/
    private void fillBuffer(double freq) {
        for (int i=0;i<audioBuffer.length/2;i++) {
            double val = Math.sin(2 * Math.PI * i / (SAMPLE_RATE/freq));
            short normVal = (short) ((val*32767));
            audioBuffer[2*i] = (byte) (normVal & 0x00ff);
            audioBuffer[2*i+1] = (byte) ((normVal & 0xff00) >>> 8);
        }
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

    public void playNote(double freq) {
        final double finFreq = freq;
        final Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                fillBuffer(finFreq);
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        playBuffer();

                    }
                });
            }
        });
        thread.start();
    }

}
