package leeco.hellogithub.sqlite.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DefaultDBHelper extends SQLiteOpenHelper {


    private static final int DB_VERSION = 1; // 数据版本
    private static final String DB_NAME = "default.db"; // 数据库名称


    public DefaultDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
