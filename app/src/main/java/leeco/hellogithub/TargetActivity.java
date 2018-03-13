package leeco.hellogithub;

import com.sunny.libcore.activity.BaseActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by zhangxin17 on 2018/3/8.
 */
public class TargetActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_target);
    }
}
