package leeco.hellogithub.utils;

import android.content.Context;

public class ContextProviderUtil {

    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getApplicationContext() {
        if (sContext == null) {
            throw new NullPointerException("Global application uninitialized");
        }
        return sContext;
    }
}
