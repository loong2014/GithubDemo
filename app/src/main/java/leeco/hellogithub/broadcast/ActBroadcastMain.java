package leeco.hellogithub.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ActBroadcastMain extends BaseActBroadcast {


    @Override
    protected void onResume() {
        super.onResume();
        doRegister();
    }


    @Override
    protected void onPause() {
        super.onPause();
        doUnregister();
    }



    private void doSend() {
        Intent intent = new Intent();

        sendBroadcast(intent);
    }


    private void doRegister() {
        IntentFilter intentFilter = new IntentFilter();


        registerReceiver(mBroadcastReceiver, intentFilter);
    }


    private void doUnregister() {
        unregisterReceiver(mBroadcastReceiver);
    }


    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };


}
