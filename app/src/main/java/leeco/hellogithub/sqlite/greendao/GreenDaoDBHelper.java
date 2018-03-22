package leeco.hellogithub.sqlite.greendao;

import org.greenrobot.greendao.database.DatabaseOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class GreenDaoDBHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "greendao.db";

    private DatabaseOpenHelper mOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public GreenDaoDBHelper(Context context) {
        mOpenHelper = new GreenDaoOpenHelper(context, DB_NAME, DB_VERSION);
    }

    public DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            mDaoMaster = new DaoMaster(mOpenHelper.getWritableDatabase());
        }
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            mDaoSession = getDaoMaster().newSession();
        }
        return mDaoSession;
    }

    public void closeConnection() {
        if (mOpenHelper != null) {
            mOpenHelper.close();
            mOpenHelper = null;
        }

        if (mDaoSession != null) {
            mDaoSession.clear();
            ;
            mDaoSession = null;
        }
    }

    /**
     * 自定义OpenHelper实现升级管理
     */
    private class GreenDaoOpenHelper extends DatabaseOpenHelper {


        public GreenDaoOpenHelper(Context context, String name, int version) {
            super(context, name, version);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // 根据版本判断数据库表是否需要升级
            if (oldVersion < DB_VERSION) {
                GreenDaoUpgradeUtil.migrate(null, GreenDaoInfoDao.class);
            }
        }
    }
}
