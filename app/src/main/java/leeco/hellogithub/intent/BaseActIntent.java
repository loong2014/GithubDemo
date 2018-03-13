package leeco.hellogithub.intent;

import android.app.Activity;
import android.util.Log;

public class BaseActIntent extends Activity {

    protected void logI(String msg) {
        Log.i("Sunny-Intent", msg);
    }
}
