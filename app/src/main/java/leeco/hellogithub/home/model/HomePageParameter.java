package leeco.hellogithub.home.model;

import leeco.hellogithub.base.BaseParameter;

/**
 * Created by zhangxin17 on 2018/1/25.
 * home页请求参数
 */
public class HomePageParameter extends BaseParameter {

    private static final String PAGE_ID = "pageId";

    private final int mPageId;

    public HomePageParameter(int pageId) {
        this.mPageId = pageId;
    }

    @Override
    public BaseParameter combineParams() {
        BaseParameter parameter = super.combineParams();
        parameter.put(PAGE_ID, this.mPageId);
        return parameter;
    }
}
