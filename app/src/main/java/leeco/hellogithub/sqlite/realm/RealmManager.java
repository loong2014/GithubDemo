package leeco.hellogithub.sqlite.realm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmManager {
    /**
     * 初始化，在Application的onCreate方法中调用
     */
    public static void init(Context context) {

        Realm.init(context); // 初始化

        // 默认Realm
        RealmConfiguration defConfig = new RealmConfiguration.Builder()
                .name("def.realm") // 指定数据库名称，默认名称是default
                .schemaVersion(1) // 指定数据库版本号
                .deleteRealmIfMigrationNeeded() // 版本冲突时自动删除原数据库，默认false。
                .inMemory() // 数据库只在内存中持久化，即应用关闭后就清除数据库
                .build();
        Realm.setDefaultConfiguration(defConfig); // 设置默认Realm
        Realm defRealm = Realm.getDefaultInstance(); // 获取默认Realm === sunny.realm

//        // 自定义Realm
//        RealmConfiguration myConfig = new RealmConfiguration.Builder()
//                .name("my.realm")
//                .schemaVersion(1)
//                .build();
//        Realm myRealm = Realm.getInstance(myConfig);
    }

    public static Realm getDefRealm() {
        return Realm.getDefaultInstance();
    }

    public static void closeDefRealm() {
        Realm.getDefaultInstance().close();
    }

}
