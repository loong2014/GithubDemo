package leeco.hellogithub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import leeco.hellogithub.base.BaseActivity;
import leeco.startup.ActStartupMain;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewClick();
    }


    private void initViewClick() {
        findViewById(R.id.btn_01).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();
        switch (view.getId()) {

            case R.id.btn_01:
                intent.setClass(this, ActStartupMain.class);
                break;
            default:
                break;
        }
        startActivity(intent);

    }


}
