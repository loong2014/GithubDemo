package leeco.hellogithub.broadcast;

import android.app.Activity;
import android.util.Log;

public class BaseActBroadcast extends Activity  {
    protected void logI(String msg) {
        Log.i("Sunny-Broadcast", msg);
    }

}
