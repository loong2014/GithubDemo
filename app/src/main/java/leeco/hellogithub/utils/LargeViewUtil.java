package leeco.hellogithub.utils;

import android.view.View;

/**
 * Created by zhangxin17 on 2018/1/25.
 * 控件放大
 */
public class LargeViewUtil {

    /**
     * 放大
     */
    public static void largeView(View v, float scale) {
        if (v != null) {
            v.setScaleX(scale);
            v.setScaleY(scale);
            v.bringToFront();
            if (v.getParent() instanceof View) {
                ((View) v.getParent()).invalidate();
            }
        }
    }

    /**
     * 还原
     */
    public static void revertView(View v) {
        if (v != null) {
            v.setScaleY(1.0F);
            v.setScaleY(1.0F);
        }
    }
}
