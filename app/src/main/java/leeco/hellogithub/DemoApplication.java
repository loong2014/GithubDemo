package leeco.hellogithub;

import android.app.Application;

import leeco.hellogithub.sqlite.realm.RealmManager;
import leeco.hellogithub.utils.ContextProviderUtil;

/**
 * Created by zhangxin17 on 2018/1/25.
 * 应用对应的applicatin
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextProviderUtil.init(this);
        RealmManager.init(this);
    }
}
