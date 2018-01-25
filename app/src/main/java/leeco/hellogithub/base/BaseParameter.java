package leeco.hellogithub.base;

import java.util.HashMap;

/**
 * Created by zhangxin17 on 2018/1/25.
 * 请求参数的基类
 */
public class BaseParameter extends HashMap<String, Object> {

    public BaseParameter combineParams() {
        BaseParameter parameter = new BaseParameter();
        return parameter;
    }
}
