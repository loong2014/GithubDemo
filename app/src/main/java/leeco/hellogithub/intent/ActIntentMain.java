package leeco.hellogithub.intent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import leeco.hellogithub.R;

public class ActIntentMain extends BaseActIntent {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        findViewById(R.id.btn_jump_target_act).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doJump();
            }
        });
    }


    private void doJump() {
        logI("doJump");
//        doJumpByComponent();
//        doJumpByAction();
//        doStartService();
//        doJumpByActionAndData();
//        doJumpByActionAndType();
        doJumpByExtra();
    }

    private void doJumpByExtra() {

        Intent intent = new Intent(this, ActIntentTarget.class);

        intent.putExtra("type", "userInfo");


        Bundle bundle = new Bundle();
        bundle.putString("name", "sunny");
        bundle.putInt("age", 18);
         intent.putExtras(bundle);

        startActivity(intent);

    }

    private void doJumpByActionAndType() {

        Intent intent = new Intent();
        intent.setAction("com.sunny.targetactivity");
        intent.addCategory("com.sunny.abc");
        intent.setDataAndType(Uri.parse("tel:num/15100010002"), "num/1*");

        startActivity(intent);

    }

    private void doJumpByActionAndData() {

        Intent intent = new Intent();
        intent.setAction("com.sunny.targetactivity");
        intent.addCategory("com.sunny.abc");
        intent.setData(Uri.parse("tel:15100010002"));

        startActivity(intent);

    }

    private void doJumpByAction() {

        Intent intent = new Intent();
        intent.setAction("com.sunny.targetactivity");
        intent.addCategory("com.sunny.abc");
        intent.setData(Uri.parse("tel:15100010002"));

        startActivity(intent);

    }

    private void doJumpByComponent() {

        Context context = getApplicationContext(); // 应用的上下文
        Class cls = ActIntentTarget.class; // 目标组件的类名

        String pkgName = context.getPackageName(); // 应用包名
        String clsName = cls.getName(); // 目标组件的绝对路径

        // 1 用于程序内组件的启动
        Intent intent1 = new Intent(context, cls);
        startActivity(intent1);

//        Intent intent2 = new Intent();
//
//        // 2 用于程序内组件的启动
//        ComponentName component1 = new ComponentName(context, cls);
//        intent2.setComponent(component1);
//
//        // 3 用于程序外组件的启动过
//        ComponentName component2 = new ComponentName(pkgName, clsName);
//        intent2.setComponent(component2);
//
//        startActivity(intent2);

    }


    private void doStartService() {
        Intent intent = new Intent(this, ServiceIntentTarget.class);
        intent.setAction("com.sunny.service");
        startService(intent);
    }

}
