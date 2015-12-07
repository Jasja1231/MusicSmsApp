package com.example.yaryna.musicsmsapp;


import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private EditText editTextNotes , editTextNumber;
    private Button buttonTestIt , buttonSendIt;
    private NotesView noteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNotes = (EditText)findViewById(R.id.edit_text_notes);
        editTextNumber = (EditText)findViewById(R.id.edit_text_number);
        noteView      = (NotesView) findViewById(R.id.note_view);

        buttonTestIt  = (Button) findViewById(R.id.button_test);
        buttonSendIt  = (Button) findViewById(R.id.button_send);

        final IncomingSms myReceiver = new IncomingSms();
        myReceiver.registerUIElements(noteView);
        myReceiver.setCurrentContex(getBaseContext());
        //Filter for intent : receive message
        IntentFilter filter = new IntentFilter(SMS_RECEIVED);
        registerReceiver(myReceiver,filter);


        //setting on click listener to button for testing sms
        buttonTestIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notesString = editTextNotes.getText().toString();
                if(notesString !=null)
                        myReceiver.drawAndPlay(notesString);
            }
        });


        //setting on click listener to sending button
        buttonSendIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notes = editTextNotes.getText().toString();
                String number =  editTextNumber.getText().toString();
                prepareToSendNotes(notes, number);
            }
        });
    }


    /**
     * Check if user SMS is valid before sending. If valid, sends text message.
     * @param contactNumber recipient number
     * @param messageText text to be send
     * @invariant.   contactNumber, messageText.
     **/
    private void prepareToSendNotes(String messageText , String contactNumber){
        SMSValidityCheck smsValidityCheck = new SMSValidityCheck(messageText);
        if(smsValidityCheck.isValid() == true && contactNumber.isEmpty() == false){
            send(messageText ,contactNumber);
        }
        else{
            if(smsValidityCheck.isValid() == false)
            Toast.makeText(this.getApplicationContext()
                    ,"Cannot send this SMS because it doesn't matches pattern"
                    ,Toast.LENGTH_LONG)
                    .show();
            else if(contactNumber.isEmpty() == true)
                Toast.makeText(this.getApplicationContext()
                        ,"Cannot send this SMS because number is not provided!"
                        ,Toast.LENGTH_LONG)
                        .show();
        }
    }

    /**Sends text message.
     * @param contactNumber recipient number
     * @param messageText text to be send
     * @invariant.   contactNumber, messageText.             
     **/
    private void send(String messageText , String contactNumber) {
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(contactNumber,
                   null, messageText, null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
