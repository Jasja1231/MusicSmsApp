package com.example.yaryna.musicsmsapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Yaryna on 20/11/2015.
 */
public class SMSValidityCheck {

    //array of splitted words from received message to check
    ArrayList<String> splitNotes;
    String messageBody;
    boolean isValidSMS;
    Context context;

    public boolean isValid(){
        return this.isValidSMS;
    }

    /**Constructor for SMS pattern check*/
    public SMSValidityCheck(String messageBody,Context context){
        this.messageBody = messageBody;
        //splitting array into separate words
        this.context = context;
        splitNotes = splitIntoArray(messageBody);
        this.isValidSMS = checkValidity(splitNotes);
    }

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
        String pattern = "[1234680]{1}[EFGABCD]{1}[b]?[45]";

        if(splitNotes.isEmpty())
            isValid = false;
        else{
            for(String s : splitNotes){
                if(!s.matches(pattern))
                    isValid = false;
                    break;
                }
        }
        return isValid;
    }




}
