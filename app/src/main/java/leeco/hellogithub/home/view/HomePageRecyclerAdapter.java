package leeco.hellogithub.home.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import leeco.hellogithub.R;
import leeco.hellogithub.base.FocusRecyclerView;
import leeco.hellogithub.base.IRecyclerViewItemListener;
import leeco.hellogithub.home.model.HomePageItemInfo;

/**
 * Created by zhangxin17 on 2018/1/25.
 * {@link FocusRecyclerView}对应的adapter
 */
public class HomePageRecyclerAdapter extends RecyclerView.Adapter implements View.OnClickListener, View.OnFocusChangeListener {

    private final Context mContext;
    private final LayoutInflater mInflater;

    private List<HomePageItemInfo> mHomePageItemInfoList;

    private IRecyclerViewItemListener mRecyclerViewItemListener;


    public HomePageRecyclerAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setItemListener(IRecyclerViewItemListener listener) {
        this.mRecyclerViewItemListener = listener;
    }

    public void setData(List<HomePageItemInfo> list) {
        mHomePageItemInfoList = list;
    }

    public HomePageItemInfo getItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            return null;
        }
        return mHomePageItemInfoList.get(position);
    }

    @Override
    public int getItemCount() {
        return mHomePageItemInfoList == null ? 0 : mHomePageItemInfoList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View baseView = mInflater.inflate(R.layout.item_home_page, parent, false);
        return new HomePageItemViewHolder(baseView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HomePageItemViewHolder) {
            HomePageItemInfo itemInfo = getItem(position);
            if (itemInfo != null) {
                HomePageItemViewHolder viewHolder = (HomePageItemViewHolder) holder;

                viewHolder.nameView.setText(itemInfo.getShowName());
                viewHolder.iconView.setImageResource(R.mipmap.ic_launcher);

//                Glide.with(mContext)
////                        .load(itemInfo.getIconUrl())
//                        .load("http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg")
//                        .into(viewHolder.iconView);


                viewHolder.rootView.setOnClickListener(this);
                viewHolder.rootView.setOnFocusChangeListener(this);
            }
        }
    }

    public class HomePageItemViewHolder extends RecyclerView.ViewHolder {
        View rootView;

        ImageView iconView;

        TextView nameView;

        HomePageItemViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            iconView = itemView.findViewById(R.id.item_home_page_icon);
            nameView = itemView.findViewById(R.id.item_home_page_name);
        }
    }

    @Override
    public void onClick(View view) {
        if (mRecyclerViewItemListener != null) {
            mRecyclerViewItemListener.onItemClick(view);
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (mRecyclerViewItemListener != null) {
            mRecyclerViewItemListener.onItemFocusChanged(view, hasFocus);
        }
    }
}
