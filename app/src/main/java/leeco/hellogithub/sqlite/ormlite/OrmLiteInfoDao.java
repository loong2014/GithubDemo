package leeco.hellogithub.sqlite.ormlite;

import com.j256.ormlite.dao.Dao;
import com.sunny.libcore.log.LogUtils;

import android.content.Context;
import android.support.annotation.Nullable;

import java.sql.SQLException;

import leeco.hellogithub.sqlite.core.BaseSqlDao;
import leeco.hellogithub.sqlite.core.SunnyDBDao;

public class OrmLiteInfoDao extends BaseSqlDao<OrmLiteInfo> {

    private OrmLiteDBHelper mOrmLiteDBHelper;
    private Dao<OrmLiteInfo, Integer> mOrmLiteTestInfoDao;

    public OrmLiteInfoDao(Context context) {

        try {
            mOrmLiteDBHelper = SunnyDBDao.INTERFACE.getOrmLiteDBHelper(context);
            mOrmLiteTestInfoDao = mOrmLiteDBHelper.getDao(OrmLiteInfo.class);
        } catch (SQLException e) {
            LogUtils.sqlLog("getDao error :" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean addDef() {
        OrmLiteInfo info = buildOrmLiteInfo();
        return add(info);
    }

    @Override
    public boolean add(OrmLiteInfo info) {
        int rst = -1;
        try {
            rst = mOrmLiteTestInfoDao.create(info);
        } catch (SQLException e) {
            LogUtils.sqlLog("create error :" + e.getMessage());
            e.printStackTrace();
        }
        return rst != -1;
    }

    @Override
    public boolean delete(int id) {
        int rst = -1;
        try {
            rst = mOrmLiteTestInfoDao.deleteById(id);
        } catch (SQLException e) {
            LogUtils.sqlLog("deleteById error :" + e.getMessage());
            e.printStackTrace();
        }
        return rst != -1;
    }

    @Override
    public boolean update(OrmLiteInfo info) {
        int rst = -1;
        try {
            rst = mOrmLiteTestInfoDao.update(info);
        } catch (SQLException e) {
            LogUtils.sqlLog("update error :" + e.getMessage());
            e.printStackTrace();
        }
        return rst != -1;
    }

    @Override
    @Nullable
    public OrmLiteInfo query(int id) {
        try {
            return mOrmLiteTestInfoDao.queryForId(id);
        } catch (SQLException e) {
            LogUtils.sqlLog("create error :" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        mOrmLiteDBHelper.close();
    }

    private OrmLiteInfo buildOrmLiteInfo() {
        long time = System.currentTimeMillis();

        long tmp = time % 100;
        String name = String.valueOf(true);
        int age = (int) (tmp % 60);
        int gender = age % 2;

        OrmLiteInfo info = new OrmLiteInfo();
        info.setUserName(name);
        info.setAge(age);
        info.setGender(gender == 1);
        return info;
    }
}
