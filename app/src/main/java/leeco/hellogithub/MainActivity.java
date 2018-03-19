package leeco.hellogithub;

import com.sunny.libcore.activity.BaseActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import leeco.hellogithub.intent.ActIntentMain;
import leeco.hellogithub.startup.ActStartupMain;
import leeco.hellogithub.theme.ActThemeMain;

public class MainActivity extends BaseActivity implements View.OnClickListener,View.OnFocusChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewClick();
    }

    private void initViewClick() {
        findViewById(R.id.btn_01).setOnClickListener(this);
        findViewById(R.id.btn_02).setOnClickListener(this);
        findViewById(R.id.btn_03).setOnClickListener(this);

//        findViewById(R.id.btn_01).setOnFocusChangeListener(this);
//        findViewById(R.id.btn_02).setOnFocusChangeListener(this);
//        findViewById(R.id.btn_03).setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();
        switch (view.getId()) {

            case R.id.btn_01:
                intent.setClass(this, ActStartupMain.class);
                break;

            case R.id.btn_02:
                intent.setClass(this, ActIntentMain.class);
                break;

            case R.id.btn_03:
                intent.setClass(this, ActThemeMain.class);
                break;

            default:
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Button btn = (Button) v;
        if (hasFocus){
          btn.setTextColor(getResources().getColor(R.color.red));
        }else {
            btn.setTextColor(getResources().getColor(R.color.block));
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
