package leeco.hellogithub.intent;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class BaseServiceIntent extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected void logI(String msg) {
        Log.i("Sunny-Intent", msg);
    }
}
