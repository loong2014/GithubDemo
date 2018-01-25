package leeco.hellogithub.base;

import android.view.View;

/**
 * Created by zhangxin17 on 2018/1/25.
 * recyclerView事件监听
 */
public interface IRecyclerViewItemListener {

    void onItemClick(View v);

    void onItemFocusChanged(View v, boolean hasFocus);

}
