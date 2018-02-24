package leeco.startup;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import leeco.hellogithub.base.BaseActivity;

public class ActBaseStartup extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logD("ActBaseStartup onCreate");
        dumpTaskAffinity();
        int layoutId = getLayoutId();
        if (layoutId == -1) {
            finish();
            return;
        }
        setContentView(layoutId);
        initViewClick();
    }

    protected int getLayoutId() {
        return -1;
    }

    protected void initViewClick() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        logD("ActBaseStartup onNewIntent");
        dumpTaskAffinity();
    }

    private void dumpTaskAffinity() {
        logD("ActBaseStartup --- --- ---");
        logD("ActBaseStartup TaskId: " + getTaskId() + " hasCode:" + this.hashCode());

        try {
            ActivityInfo info = this.getPackageManager()
                    .getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            logD("ActBaseStartup taskAffinity - " + info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        logD("ActBaseStartup           --- --- ---");
    }
}
