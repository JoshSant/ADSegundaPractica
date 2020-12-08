package com.example.adsegundapractica.view.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.adsegundapractica.model.AmigoRepository;
import com.example.adsegundapractica.view.activity.AmigosActivity;
import com.example.adsegundapractica.viewmodel.AndroidAmigoViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IncomingCall extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        try {
            TelephonyManager tmgr = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            MyPhoneStateListener PhoneListener = new MyPhoneStateListener();

            tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        } catch (Exception e) {
            Log.v("Phone Receive Error", " " + e);
        }

    }

    private class MyPhoneStateListener extends PhoneStateListener {

        public void onCallStateChanged(int state, String incomingNumber) {

            Log.v("MyPhoneListener",state+"   incoming no:"+incomingNumber);

            if (state == 1) {

                long date = System.currentTimeMillis();

                SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
                String dateString = sdf.format(date);
                Log.v("xyz", "Llamada:" + incomingNumber + "," + dateString);
                AmigosActivity.llamada(incomingNumber, dateString);

            }
        }
    }
}
