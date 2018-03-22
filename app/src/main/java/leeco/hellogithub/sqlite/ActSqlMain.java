package leeco.hellogithub.sqlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import leeco.hellogithub.R;
import leeco.hellogithub.sqlite.core.ISqlDao;
import leeco.hellogithub.sqlite.realm.RealmInfoDao;

public class ActSqlMain extends BaseActSql implements View.OnClickListener {

    private ISqlDao mSqlDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_main);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);

//        mSqlDao = new OrmLiteInfoDao(this);

//        mSqlDao = new GreenDaoInfoDaoUtil(this);

        mSqlDao = new RealmInfoDao();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                mSqlDao.addDef();
                break;

            case R.id.btn_delete:
                break;

            case R.id.btn_update:
                break;

            case R.id.btn_query:
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSqlDao.close(); // call for Realm
    }
}

