package com.example.vaio.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by vaio on 11/16/2016.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void
    onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {Toast.makeText(context,"Airplane mode changed",Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(context, "Airplane mode changed", Toast.LENGTH_SHORT).show();

        if (action.equals(Intent.ACTION_SCREEN_ON)) {
            Toast.makeText(context, "Screen On", Toast.LENGTH_SHORT).show();
        }

        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            Toast.makeText(context, "Screen Off", Toast.LENGTH_SHORT).show();
        }

        if (action.equals(Intent.ACTION_BATTERY_LOW)) {
            Toast.makeText(context, "Battery Low", Toast.LENGTH_SHORT).show();
        }
    }
}
