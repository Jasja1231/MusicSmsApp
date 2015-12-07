package com.example.yaryna.musicsmsapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

/**
 * This class check is received text matched notes pattern.
 * Created by 15038588
 */
public class SMSValidityCheck {

    //array of split words from received message to check
    ArrayList<String> splitNotes;
    String messageBody;
    private boolean isValidSMS;


    /**
     * Gatter for isValidSMS
     * @return true if valid
     *         false otherwise
     **/
    public boolean isValid(){
        return this.isValidSMS;
    }
    public ArrayList<String> getSplitNotes(){return splitNotes;}

    /**Constructor for SMS pattern check
     * @param. messageBody string got from SMS message to be checked.
     * */
    public SMSValidityCheck(String messageBody){
        this.messageBody = messageBody;
        //splitting array into separate words
        splitNotes = splitIntoArray(messageBody);
        this.isValidSMS = checkValidity(splitNotes);
    }

    /**
     * Separates words in a single string with space separator.
     * @pre. messageToSplit!=null.
     * @param. messageToSplit string to split into array of words.
     * @return. ArrayList<String> words in original string separated by space.
     * */
    private ArrayList<String> splitIntoArray(String messageToSplit){
        ArrayList<String> result = new ArrayList<>();
        try {
            String[] splitArray = messageToSplit.split("\\s");
            for (String s : splitArray) {
                result.add(s);
            }
        } catch (PatternSyntaxException ex) {
            Log.e("PatternSyntaxException:","PatternSyntaxException: " + ex);
        }
        return result;
    }


    /**Checks words in array to match notes pattern defined for each string as follows :
     * 1 character - The first digit is 1,2,3,4,6, or 8 and determines the duration of the note, expressed in quavers
     * 2 character - The second character, the optional ‘#’, or ‘b’,
     * 3 character - final digit express the note in scientific pitch notation
     * */
    private boolean checkValidity(ArrayList<String> splitNotes ){
        boolean isValid = true;

        /**
         * The notes pattern string should follow.
         */
        String pattern   = "[123468]{1}[EFGAB]{1}[4]{1}";       // REGULAR NOTES
        String pattern1  = "[123468]{1}[EFCD]{1}[5]{1}";        // REGULAR NOTES
        String pattern2  = "[123468]{1}[GAEBF]{1}[b]{1}[4]{1}";    //FLATS FROM 4 OCTAVE
        String pattern3  = "[123468]{1}[GABDCE]{1}[b]{1}[5]{1}";   //FLATS FROM 5 OCTAVE

        if(splitNotes.isEmpty())
            isValid = false;
        else{
            for(String s : splitNotes){
                if(!s.matches(pattern) &&  !s.matches(pattern1) && !s.matches(pattern2) && !s.matches(pattern3) )
                    isValid = false;
                    break;
                }
        }
        return isValid;
    }




}
