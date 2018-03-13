package leeco.hellogithub.provider;

import android.app.Activity;
import android.util.Log;

public class BaseActProvider extends Activity {
    protected void logI(String msg) {
        Log.i("Sunny-Provider", msg);
    }

}
