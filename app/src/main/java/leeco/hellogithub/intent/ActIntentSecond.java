package leeco.hellogithub.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import leeco.hellogithub.R;

public class ActIntentSecond extends BaseActIntent {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_target);

        logI("onCreate");
        doParseIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        logI("onNewIntent");
        doParseIntent(intent);
    }

    private void doParseIntent(Intent intent) {
        logI("doParseIntent  intent :" + intent);

    }
}
