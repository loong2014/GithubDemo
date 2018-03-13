package leeco.hellogithub;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by zhangxin17 on 2018/3/8.
 */
public class TargetService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyService();
    }

    private class MyService extends Binder {
        public TargetService getService() {
            return TargetService.this;
        }
    }
}
