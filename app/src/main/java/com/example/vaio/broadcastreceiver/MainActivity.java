package com.example.vaio.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String ACTION_SEND_NUMBER = "action_send_number";
    private EditText edt;
    private Button btn;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(ACTION_SEND_NUMBER);
        registerReceiver(receiver, intentFilter1);

        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private void initViews() {
        edt = (EditText) findViewById(R.id.edt);
        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String number = edt.getText().toString();
        if (number.isEmpty()) {
            Toast.makeText(this, "Number must not empty", Toast.LENGTH_SHORT).show();
            return;
        }
        int num = Integer.parseInt(number);
        Intent intent = new Intent();
        intent.setAction(MyService.ACTION_RECEIVE_NUMBER);
        intent.putExtra(MyService.KEY_NUMBER,num);
        sendBroadcast(intent);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ACTION_SEND_NUMBER)) {
                int number = intent.getExtras().getInt(MyService.KEY_NUMBER);
                tv.setText(number + "");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
