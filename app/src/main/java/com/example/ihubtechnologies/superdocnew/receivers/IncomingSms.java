package com.example.ihubtechnologies.superdocnew.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class IncomingSms extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    Log.d("otpmessage",currentMessage.getDisplayMessageBody());
                    String message = currentMessage.getDisplayMessageBody().split(" : ")[1];


                    String message1 = message.substring(0, message.length()-3);
                    String message2 = message.substring(1, message.length()-2);
                    String message3 = message.substring(2, message.length()-1);
                    String message4 = message.substring(3, message.length()-0);
                    Log.d("messageee2",message1+message2+message3+message4);
                    Log.d("messageee",message.toString());
                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

                    Intent myIntent = new Intent("otp");
                    myIntent.putExtra("message",message);
                    myIntent.putExtra("message1",message1);
                    myIntent.putExtra("message2",message2);
                    myIntent.putExtra("message3",message3);
                    myIntent.putExtra("message4",message4);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(myIntent);
                    // Show Alert

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }
}
