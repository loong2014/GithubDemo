package leeco.hellogithub.base;

import android.content.Context;

/**
 * Created by zhangxin17 on 2018/1/25.
 * 数据请求的基类
 */
public class BaseRequest {
    private final Context mContext;
    private final TaskCallBack mTaskCallBack;

    public BaseRequest(Context context, TaskCallBack callBack) {
        this.mContext = context;
        this.mTaskCallBack = callBack;
    }


    public void doRequest(BaseParameter parameter) {

    }
}
