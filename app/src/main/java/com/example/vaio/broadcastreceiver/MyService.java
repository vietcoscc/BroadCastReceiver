package com.example.vaio.broadcastreceiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by vaio on 11/16/2016.
 */

public class MyService extends Service {
    public static final String ACTION_RECEIVE_NUMBER = "action_receive_number";
    public static final String KEY_NUMBER = "1";
//    private MyReceiver myReceiver;
    private int number;
    private boolean isRunning;

    @Override
    public void onCreate() {
        super.onCreate();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
//        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
//        myReceiver = new MyReceiver();
//        registerReceiver(myReceiver, intentFilter);

        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(ACTION_RECEIVE_NUMBER);
        registerReceiver(receiver,intentFilter1);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread thread  =new Thread(runnable);
        thread.start();
        return START_STICKY;
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            isRunning = true;
            while (isRunning){
                for (int i=1;i<number;i++){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(MainActivity.ACTION_SEND_NUMBER);
                    sendIntent.putExtra(KEY_NUMBER,i);
                    sendBroadcast(sendIntent);
                }
            }
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(myReceiver);
        unregisterReceiver(receiver);
    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(ACTION_RECEIVE_NUMBER)){
                number = intent.getExtras().getInt(KEY_NUMBER);
            }
        }
    };
}
