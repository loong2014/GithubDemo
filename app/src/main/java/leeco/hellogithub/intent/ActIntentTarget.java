package leeco.hellogithub.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import leeco.hellogithub.R;

public class ActIntentTarget extends BaseActIntent {

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
        if (intent == null) {
            return;
        }

        if (intent.getExtras() == null) {
            return;
        }

        String type = intent.getStringExtra("type"); // userInfo

        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name"); // sunny
        int age = bundle.getInt("age"); // 18


        logI("type :" + type + "  name :" + name + "  age :" + age);

        Uri uri = intent.getData();
        String scheme = uri.getScheme(); // tel
        String data = uri.getSchemeSpecificPart(); // 15100010002


        logI("doParseIntent  uri :" + uri +
                "\n  getScheme :" + scheme +
                "\n  getSchemeSpecificPart :" + data +
                "\n  getFragment :" + uri.getFragment() +
                "\n  getEncodedPath :" + uri.getEncodedPath() + "" +
                "\n  getEncodedSchemeSpecificPart :" + uri.getEncodedSchemeSpecificPart() +
                "\n  getLastPathSegment :" + uri.getLastPathSegment()
        );

    }
}
