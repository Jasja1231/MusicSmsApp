package com.example.yaryna.musicsmsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by 15038588
 */
public class IncomingSms extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    private Context currentContext;
    private NotesView notesView;

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

                    Toast.makeText(context,"i ::: " + i + " message: " + message + "Received",  Toast.LENGTH_SHORT).show();
                    processMessage(currentMessage);
                 }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }

    private void processMessage(SmsMessage currentMessage){
        String messageBody = currentMessage.getDisplayMessageBody().toString();
        SMSValidityCheck smsValidityCheck = new SMSValidityCheck(messageBody,currentContext);
        if(smsValidityCheck.isValid() == true){
            ArrayList<String> noteStrings = smsValidityCheck.getSplitNotes();
            NotesConstructor notesConstructor = new NotesConstructor(noteStrings);

            //GENERATE SOUNDS
            ArrayList<NoteInstance> notes = notesConstructor.getNotes();
            this.notesView.setNotesArray(notes);
            SoundMaker soundMaker = new SoundMaker(notes);
            soundMaker.playNotes();

        }
        else{
            Toast.makeText(currentContext,
                    "Received SMS does not match notes pattern!"
                    ,Toast.LENGTH_LONG)
                    .show();
        }
    }


    public void registerCurrentNotesView(NotesView notesView){
        this.notesView = notesView;
    }
}