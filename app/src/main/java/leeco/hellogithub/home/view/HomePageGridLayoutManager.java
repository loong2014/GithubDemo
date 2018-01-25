package leeco.hellogithub.home.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

import leeco.hellogithub.base.FocusRecyclerView;

/**
 * Created by zhangxin17 on 2018/1/25.
 * {@link FocusRecyclerView}对应的layoutManager
 */
public class HomePageGridLayoutManager extends GridLayoutManager {

    public HomePageGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public HomePageGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public HomePageGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }
}
