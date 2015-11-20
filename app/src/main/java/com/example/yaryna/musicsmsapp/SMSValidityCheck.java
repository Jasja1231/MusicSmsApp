package com.example.yaryna.musicsmsapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Yaryna on 20/11/2015.
 */
public class SMSValidityCheck {

    //array of splitted words from received message to check
    ArrayList<String> splitNotes;
    String messageBody;

    /**Constructor for SMS pattern check*/
    public SMSValidityCheck(String messageBody){
        this.messageBody = messageBody;
        //splitting array into separate words
        splitNotes = splitIntoArray(messageBody);
    }

    private ArrayList<String> splitIntoArray(String messageToSplit){
        ArrayList<String> result = new ArrayList<>();
        try {
            String[] splitArray = messageToSplit.split("\\s");
        } catch (PatternSyntaxException ex) {
            Log.e("PatternSyntaxException:","PatternSyntaxException: " + ex);
        }
        return result;
    }




}
