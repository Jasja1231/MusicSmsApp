package com.example.yaryna.musicsmsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by 15038588
 */
public class IncomingSms extends BroadcastReceiver {

    final String ERROR_SMS = "Sms message received doesn't match notes pattern!";
    final String ERROR_TEST = "Notes you want to send don't match notes pattern!";

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    private Context currentContext;
    private NotesView notesView;

    SoundMaker soundMaker = new SoundMaker();

    public NotesView getNoteView(){return notesView;}

   // private EditText editableNotes , editableNumber;
   // private Button   buttonTestIt  , buttonSendIt  ;

    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                // Retrieve the SMS Messages received
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String message = currentMessage.getDisplayMessageBody().toString();
                    Log.i("SmsReceiver", " message: " + message);
                    currentContext = context;
                    processMessageSMS(currentMessage);
                 }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }

    public void  setCurrentContex(Context co){
        this.currentContext = co;
    }
    private void processMessageSMS(SmsMessage currentMessage){
        String messageBody = currentMessage.getDisplayMessageBody().toString();
        processMessage(messageBody,ERROR_SMS);

    }

    public void drawAndPlay(String testMelodyString){
        processMessage(testMelodyString,ERROR_TEST);
    }

    private void processMessage(String messageBody , String errorMessage){
        SMSValidityCheck smsValidityCheck = new SMSValidityCheck(messageBody);
        if(smsValidityCheck.isValid() == true){
            ArrayList<String> noteStrings = smsValidityCheck.getSplitNotes();
            NotesConstructor notesConstructor = new NotesConstructor(noteStrings);
            ArrayList<NoteInstance> notes = notesConstructor.getNotes();


            this.notesView.setNotesArray(notes);
            while(this.notesView.finished != true){
                soundMaker.playNotes(notes);
                break;
            }
        }
        else{
            if(errorMessage!=ERROR_SMS)
            Toast.makeText(currentContext,
                    errorMessage
                    ,Toast.LENGTH_LONG)
                    .show();
        }
    }


    public void registerUIElements(NotesView notesView/*, EditText e1 ,EditText e2,Button buttonTestIt , Button buttonSendIt*/ ){
        this.notesView = notesView;
    }

}