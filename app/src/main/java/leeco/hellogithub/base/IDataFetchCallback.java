package leeco.hellogithub.base;

/**
 * Created by zhangxin17 on 2018/1/25.
 * 数据请求回调接口
 */
public interface IDataFetchCallback {

    /**
     * 开始数据请求
     */
    void onStartDateFetch(String msg);

    /**
     * 数据请求失败
     */
    void onDateFetchFail(int code, final String msg, String errorCode);

    /**
     * 数据请求成功
     *
     * @param arg 额外参数
     * @param o   数据信息
     */
    void onDateFetchSuccess(int arg, Object o);
}
