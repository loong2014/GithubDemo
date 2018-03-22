package leeco.hellogithub.sqlite.greendao;

import com.sunny.libcore.log.LogUtils;

import android.content.Context;

import leeco.hellogithub.sqlite.core.BaseSqlDao;
import leeco.hellogithub.sqlite.core.SunnyDBDao;

public class GreenDaoInfoDaoUtil extends BaseSqlDao<GreenDaoInfo> {

    private GreenDaoDBHelper mGreenDaoDBHelper;
    private DaoSession mDaoSession;
    private GreenDaoInfoDao mGreenDaoInfoDao;

    public GreenDaoInfoDaoUtil(Context context) {
        mGreenDaoDBHelper = SunnyDBDao.INTERFACE.getGreenDaoDBHelper(context);
        mDaoSession = mGreenDaoDBHelper.getDaoSession();
        mGreenDaoInfoDao = mDaoSession.getGreenDaoInfoDao();
    }

    @Override
    public boolean addDef() {
        GreenDaoInfo info = buildGreenDaoInfo();
        return add(info);
    }

    @Override
    public boolean add(GreenDaoInfo info) {
        long rst = -1;
        rst = mDaoSession.insert(info);
//        rst = mGreenDaoInfoDao.insert(info);
        return rst != -1;
    }

    @Override
    public boolean delete(GreenDaoInfo info) {
        try {
            mDaoSession.delete(info);
//            mGreenDaoInfoDao.delete(info);
            return true;
        } catch (Exception e) {
            LogUtils.sqlLog("delete error :" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(GreenDaoInfo info) {

        try {
            mDaoSession.update(info);
//            mGreenDaoInfoDao.update(info);
            return true;
        } catch (Exception e) {
            LogUtils.sqlLog("delete error :" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public GreenDaoInfo query(long id) {
        GreenDaoInfo info = null;
        try {
            info = mDaoSession.load(GreenDaoInfo.class, id);
//            info = mGreenDaoInfoDao.load(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public void close() {
        mGreenDaoDBHelper.closeConnection();
    }

    private GreenDaoInfo buildGreenDaoInfo() {
        long time = System.currentTimeMillis();

        long tmp = time % 100;
        String name = String.valueOf(true);
        int age = (int) (tmp % 60);
        int gender = age % 2;

        GreenDaoInfo info = new GreenDaoInfo();
        info.setUserName(name);
        info.setAge(age);
        info.setGender(gender == 1);
        return info;
    }


}
