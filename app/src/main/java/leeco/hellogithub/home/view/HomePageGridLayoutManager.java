package leeco.hellogithub.home.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

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

    private ILayoutManagerCallback mLayoutManagerCallback;

    public interface ILayoutManagerCallback {
        View getTabView();
    }

    public void setLayoutManagerCallback(ILayoutManagerCallback callback) {
        this.mLayoutManagerCallback = callback;
    }


    @Override
    public View onInterceptFocusSearch(View focused, int direction) {
        if (direction == View.FOCUS_UP) {
            logI("onInterceptFocusSearch  mLayoutManagerCallback :" + mLayoutManagerCallback);
            if (mLayoutManagerCallback != null) {
                View nextView = mLayoutManagerCallback.getTabView();
                logI("onInterceptFocusSearch  nextView :" + nextView);
                return nextView;
            }

        }
        return super.onInterceptFocusSearch(focused, direction);
    }


    private void logI(String msg) {
        Log.i("Sunny", msg);
    }

}
