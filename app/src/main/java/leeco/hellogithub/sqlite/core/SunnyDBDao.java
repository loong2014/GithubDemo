package leeco.hellogithub.sqlite.core;

import android.content.Context;

import leeco.hellogithub.sqlite.greendao.GreenDaoDBHelper;
import leeco.hellogithub.sqlite.ormlite.OrmLiteDBHelper;

public enum SunnyDBDao {
    INTERFACE;

    private OrmLiteDBHelper mOrmLiteDBHelper;

    public OrmLiteDBHelper getOrmLiteDBHelper(Context context) {
        if (mOrmLiteDBHelper == null) {
            mOrmLiteDBHelper = new OrmLiteDBHelper(context);
        }
        return mOrmLiteDBHelper;
    }


    private GreenDaoDBHelper mGreenDaoDBHelper;

    public GreenDaoDBHelper getGreenDaoDBHelper(Context context) {
        if (mGreenDaoDBHelper == null) {
            mGreenDaoDBHelper = new GreenDaoDBHelper(context);
        }
        return mGreenDaoDBHelper;
    }


}
