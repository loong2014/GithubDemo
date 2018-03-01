package leeco.hellogithub.home.model;

import android.content.Context;

import com.sunny.libcore.http.BaseRequest;
import com.sunny.libcore.http.TaskCallBack;

/**
 * Created by zhangxin17 on 2018/1/25.
 * home页数据请求
 */
public class HomePageDataRequest extends BaseRequest {

    public HomePageDataRequest(Context context, TaskCallBack callBack) {
        super(context, callBack);
    }

}
