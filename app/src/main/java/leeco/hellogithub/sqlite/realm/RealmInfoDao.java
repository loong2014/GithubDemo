package leeco.hellogithub.sqlite.realm;

import com.sunny.libcore.log.LogUtils;

import io.realm.Realm;
import io.realm.RealmResults;
import leeco.hellogithub.sqlite.core.BaseSqlDao;

public class RealmInfoDao extends BaseSqlDao<RealmInfo> {


    @Override
    public boolean addDef() {
        try {

            // 新建数据对象进行存储
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    // 1. 构建info并赋值
                    RealmInfo info = realm.createObject(RealmInfo.class, 101);
                    info.setUserName("sunny");
                    info.setAge(18);

                    // 2. 根据json构建info
                    realm.createAllFromJson(RealmInfo.class, "{}");
                }
            });

            // 复制对象到数据库
            final RealmInfo info = buildRealmInfo();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(info);
                }
            });

            return true;
        } catch (Exception e) {
            LogUtils.sqlLog("add error :" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean add(final RealmInfo info) {

        try {

            // 1. 使用事务
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.copyToRealm(info);
            realm.commitTransaction();

            // 2. 使用事务块——推荐使用封装好的事务块
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(info);
                }
            });


            return true;
        } catch (Exception e) {
            LogUtils.sqlLog("add error :" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Realm realm = Realm.getDefaultInstance();

        // 先查找，再删除
        final RealmInfo info = query(id);
        final RealmResults<RealmInfo> infos = realm.where(RealmInfo.class).findAll();

        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    info.deleteFromRealm(); // 单个删除

                    infos.deleteAllFromRealm(); // 删除所有
                    infos.deleteFirstFromRealm(); // 删除第一个
                    infos.deleteLastFromRealm(); // 删除最后一个
                    infos.deleteFromRealm(0); // 删除第N个
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(final RealmInfo info) {

        try {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(info);
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public RealmInfo query(int id) {
        Realm realm = Realm.getDefaultInstance();

        // 1. 查询所有
        RealmResults<RealmInfo> realmInfos = realm.where(RealmInfo.class).findAll();

        // 2. 查询第一条
        RealmInfo info_1 = realm.where(RealmInfo.class).findFirst();

        // 根据条件查询，在1，2进行find前设置查询条件
        RealmInfo info = realm.where(RealmInfo.class)
                .equalTo("name", "sunny")
                .findFirst();

        return info;
    }

    @Override
    public void close() {
        Realm.getDefaultInstance().close();
    }

    private RealmInfo buildRealmInfo() {
        long time = System.currentTimeMillis();

        long tmp = time % 100;
        String name = String.valueOf(true);
        int age = (int) (tmp % 60);
        int gender = age % 2;

        RealmInfo info = new RealmInfo();
        info.setUserName(name);
        info.setAge(age);
        info.setGender(gender == 1);
        return info;
    }
}
