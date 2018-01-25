package leeco.hellogithub.home.model;

/**
 * Created by zhangxin17 on 2018/1/25.
 * home页面数据信息
 */
public class HomePageItemInfo {
    private int itemId;
    private String showName;
    private String iconUrl;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
