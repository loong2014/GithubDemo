package leeco.hellogithub.base;

/**
 * Created by zhangxin17 on 2018/1/25.
 * 网络请求返回数据格式
 */
public interface TaskCallBack {

    int CODE_OK = 0;

    void callback(int code, String msg, String errorCode, Object object);
}
