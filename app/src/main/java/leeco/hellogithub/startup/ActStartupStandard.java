package leeco.hellogithub.startup;

import android.content.Intent;
import android.view.View;

import leeco.hellogithub.R;

public class ActStartupStandard extends ActBaseStartup implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_startup_standard;
    }

    @Override
    protected void initViewClick() {
        findViewById(R.id.btn_start_self).setOnClickListener(this);
        findViewById(R.id.btn_start_other).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_start_self) {
            Intent intent = new Intent(this, ActStartupStandard.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn_start_other) {
            Intent intent = new Intent(this, ActStartupOther.class);
            startActivity(intent);
        }
    }
}