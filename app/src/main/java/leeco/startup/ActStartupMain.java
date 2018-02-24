package leeco.startup;

import android.content.Intent;
import android.view.View;

import leeco.hellogithub.R;

public class ActStartupMain extends ActBaseStartup implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_startup_main;
    }

    @Override
    protected void initViewClick() {
        findViewById(R.id.btn_standard).setOnClickListener(this);
        findViewById(R.id.btn_singleTop).setOnClickListener(this);
        findViewById(R.id.btn_singleTask).setOnClickListener(this);
        findViewById(R.id.btn_singleInstance).setOnClickListener(this);

        findViewById(R.id.btn_start_other).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();
        switch (view.getId()) {

            case R.id.btn_standard:
                logI("onClick  btn_standard");
                intent.setClass(this, ActStartupStandard.class);
                break;

            case R.id.btn_singleTop:
                logI("onClick  btn_singleTop");
                intent.setClass(this, ActStartupSingleTop.class);
                break;

            case R.id.btn_singleTask:
                logI("onClick  btn_singleTask");
                intent.setClass(this, ActStartupSingleTask.class);
                break;

            case R.id.btn_singleInstance:
                logI("onClick  btn_singleInstance");
                intent.setClass(this, ActStartupSingleInstance.class);
                break;

            case R.id.btn_start_other:
                logI("onClick  btn_start_other");
                intent.setClass(this, ActStartupOther.class);
                break;

            default:
                return;
        }

        startActivity(intent);
    }

}
