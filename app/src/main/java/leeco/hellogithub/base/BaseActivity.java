package leeco.hellogithub.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by zhangxin17 on 2018/1/25.
 * activity的基类
 */
public class BaseActivity extends AppCompatActivity {

    private final String className = getClass().getSimpleName();

    private boolean DEBUG = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logD("onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logD("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logD("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logD("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logD("onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        logD("onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logD("onStop");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        logD("onBackPressed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logD("onDestroy");
    }

    protected void logI(String msg) {
        Log.i("Sunny-" + className, msg);
    }

    protected void logD(String msg) {
        if (DEBUG) {
            Log.d("Sunny-" + className, msg);
        }
    }
}
