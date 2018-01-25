package leeco.hellogithub.home.model;

import java.util.List;

/**
 * Created by zhangxin17 on 2018/1/25.
 * home页数据返回值
 */
public class HomePageResponse {

    private List<HomePageItemInfo> list;

    public void setList(List<HomePageItemInfo> list) {
        this.list = list;
    }

    public List<HomePageItemInfo> getList() {
        return list;
    }
}
