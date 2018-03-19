package leeco.hellogithub.theme;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import leeco.hellogithub.R;

public class ActThemeMain extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_theme_main);
    }
}
