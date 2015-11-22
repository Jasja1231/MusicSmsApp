package com.example.yaryna.musicsmsapp;

/**
 * Created by Yaryna on 21/11/2015.
 */


/**Class stores frequency values for every tone,returns frequency for note*/
public  class SoundConstructor {

    /**
     A:  400.00 Hz
     A#: 466.16 Hz
     B:  493.88 Hz

     C:  523.25 Hz
     C#: 554.37 Hz
     D:  587.33 Hz
     D#: 622.25 Hz
     E:  659.26 Hz
     F:  698.46 Hz
     F#: 739.99 Hz
     G:  783.99 Hz
     G#: 830.61 Hz

     The frequencies of notes in the next octave can be obtained
     by doubling those shown above; those for the octave below
     concert A can be obtained by halving, and so on.
     */

    //3 octave
    final double A3      = 400     ;//Hz  la
    final double Bb3     = 466.16  ;//Hz
    final double B3      = 493.88  ;//Hz  si
    //4 octave
    final double C4      = 523.25  ;//Hz  do
    final double Db4     = 554.37  ;//Hz  do
    final double D4      = 587.33  ;//Hz  re
    final double Eb4     = 622.25  ;//Hz  re
    final double E4      = 659.26  ;//Hz  mi
    final double F4      = 698.46  ;//Hz  fa
    final double Gb4     = 739.99  ;//Hz
    final double G4      = 783.99  ;//Hz  sol'
    final double Ab4     = 830.61  ;//Hz
    final double A4      = A3  * 2 ;//Hz  la
    final double Bb4     = Bb3 * 2 ;//Hz
    final double B4      = B3  * 2 ;//Hz  si
    //5 octave
    final double C5      = C4  * 2  ;//Hz  do
    final double Db5     = Db4 * 2  ;//Hz  do
    final double D5      = D4  * 2  ;//Hz  re
    final double Eb5     = Eb4 * 2  ;//Hz  re
    final double E5      = E4  * 2  ;//Hz  mi
    final double F5      = F4  * 2  ;//Hz   fa
    final double Gb5     = Gb4 * 2  ;//Hz
    final double G5      = G4  * 2  ;//Hz  sol'
    final double Ab5     = Ab4 * 2  ;//Hz
    final double A5      = A4  * 2  ;//Hz  la
    final double Bb5     = Bb4 * 2  ;//Hz
    final double B5      = B4  * 2  ;//Hz  si



    public double getSpecificSoundFrequency(String note){
        double result = 0 ;
       switch(note){
           case "Eb4":
               result = Eb4;
               break;
           case "E4":
               result = E4;
               break;
           case "Gb4":
               result = Gb4;
                break;
           case "G4":
               result = G4;
               break;
           case "Bb4":
               result = Bb4;
               break;
           case "B4":
               result = B4;
               break;
           case "Db5":
               result = Db5;
                break;
           case "D5":
               result = D5;
               break;
           case "F5":
               result = F5;
               break;
           case "F4":
               result = F4;
               break;
           case "Ab4":
               result = Ab4;
                break;
           case "A4":
               result = A4;
               break;
           case "C5":
               result = C5;
               break;
           case "Eb5":
               result = Eb5;
                break;
           case "E5":
               result = E5;
               break;
           case "Cb4":
               result = B3;
               break;
           case"Cb5":
               result = B4;
               break;
           default:
               break;
       }
        return result;
    }

}
