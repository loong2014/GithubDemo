package leeco.hellogithub.home;

/**
 * Created by zhangxin17 on 2018/1/25.
 * home页面的presenter需要实现的接口
 */
public interface HomePagePresenterInterface {

    /**
     * 首次进入以及再次进入页面时触发
     */
    void doRequestDataByResume();

}
