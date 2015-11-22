package com.example.yaryna.musicsmsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Yaryna on 20/11/2015.
 */
public class IncomingSms extends BroadcastReceiver {

    android.os.Handler handler = new android.os.Handler();
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    Context currentContext;
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




                    Toast.makeText(context," message: " + message + "Received",  Toast.LENGTH_SHORT).show();
                    processMessage(currentMessage);
                    SoundMaker soundMaker = new SoundMaker(4);
                            soundMaker.playNote(480);


                }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }

    private void processMessage(SmsMessage currentMessage){
        String messageBody = currentMessage.getDisplayMessageBody().toString();
        SMSValidityCheck smsValidityCheck = new SMSValidityCheck(messageBody,currentContext);
        if(smsValidityCheck.isValidSMS == true){
            //procede with creating notes
        }
        else{
            Toast.makeText(currentContext,
                    "Received SMS does not match notes pattern!"
                    ,Toast.LENGTH_LONG)
                    .show();
        }
    }


}