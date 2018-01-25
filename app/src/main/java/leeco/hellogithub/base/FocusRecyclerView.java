package leeco.hellogithub.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by zhangxin17 on 2018/1/25.
 * recyclerView的基类
 */
public class FocusRecyclerView extends RecyclerView {
    public FocusRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public FocusRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FocusRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        /**
         * 设置子view绘制顺序从{@link #getChildDrawingOrder(int, int)}方法中获取
         */
        setChildrenDrawingOrderEnabled(true);
    }

    /**
     * 重新指定item的绘制顺序，防止选中item被其它item遮挡
     */
    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int focusPosition = indexOfChild(getFocusedChild());
        if (focusPosition >= 0) {
            if (i == childCount - 1) {
                if (focusPosition > i) {
                    focusPosition = i;
                }
                return focusPosition;
            }
            if (i == focusPosition) {
                return childCount - 1;
            }
        }
        return super.getChildDrawingOrder(childCount, i);
    }

}
