package leeco.hellogithub.intent;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ServiceIntentTarget extends BaseServiceIntent {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyService();
    }

    private class MyService extends Binder {
        public ServiceIntentTarget getService() {
            return ServiceIntentTarget.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        logI("onCreate");
    }
}
