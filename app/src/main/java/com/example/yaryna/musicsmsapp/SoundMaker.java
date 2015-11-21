package com.example.yaryna.musicsmsapp;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Handler;


/**
 * Created by 15038588
 */
public class SoundMaker {

    private  int sampleRate ;
    private  int SAMPLE_SIZE;
    private  byte[] audioBuffer;
    private  Handler handler = new Handler();

    public SoundMaker(int noteDuration){
        sampleRate = 8000;
        SAMPLE_SIZE = noteDuration*sampleRate;
        audioBuffer = new byte[2*SAMPLE_SIZE];
    }

    /**The fillBuffer() method creates a buffer with values
     * representing a sinusoidal wave of a given frequency*/
    private void fillBuffer(double frequency) {
        for (int i=0;i<audioBuffer.length/2;i++) {
            double val = Math.sin(2 * Math.PI * i / (sampleRate/frequency));
            short normVal = (short) ((val*32767));
            audioBuffer[2*i] = (byte) (normVal & 0x00ff);
            audioBuffer[2*i+1] = (byte) ((normVal & 0xff00) >>> 8);
        }
    }

    /**The playBuffer() plays that buffer using an AudioTrack object,and the
     *  playNote() method uses the previous two methods to play a note*/
    private void playBuffer() {
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_OUT_MONO,
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
