package leeco.hellogithub.sqlite.ormlite;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sunny.libcore.log.LogUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by zhangxin17 on 2018/3/20.
 * 自定义数据库管理类
 */
public class OrmLiteDBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "ormlite.db"; // 数据库名称

    private static final int DB_VERSION = 4; // 当前数据库版本

    // OrmLiteInfo 的数据表，对应的升级记录。如果本次的数据库升级涉及到该表，则将数据库版本加入
    // 这里表示，该表在数据库版本为4时，需要对该表进行升级。
    private final int[] OrmLiteInfoTableVersions = new int[]{4};

    public OrmLiteDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        LogUtils.sqlLog("OrmLiteDBHelper  create db :" + DB_NAME + "  version :" + DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        LogUtils.sqlLog("onCreate  OrmLiteInfo table");
        try {
            TableUtils.createTable(connectionSource, OrmLiteInfo.class);
        } catch (SQLException e) {
            LogUtils.sqlLog("createTable error :" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            // 判断OrmLiteInfo对应的数据表是否需要升级
            int len = OrmLiteInfoTableVersions.length;
            if (len != 0 && oldVersion < OrmLiteInfoTableVersions[len - 1]) {

                // 1. 删除旧表，同时表数据都会被删除
//                TableUtils.dropTable(connectionSource, OrmLiteInfo.class, true);

                // 2. 更新表，将旧的表数据进行迁移，DatabaseUtil是自己封装的数据迁移工具类
                // ADD表示添加表字段，DELETE表示删除表字段
                OrmLiteUpgradeUtil.upgradeTable(sqLiteDatabase, connectionSource, OrmLiteInfo.class,
                        OrmLiteUpgradeUtil.OPERATION_TYPE.DELETE);
            }

            // 判断其它数据库表是否需要升级

            this.onCreate(sqLiteDatabase, connectionSource);
        } catch (Exception e) {
            LogUtils.sqlLog("dropTable error :" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
        LogUtils.sqlLog("close");
    }
}
