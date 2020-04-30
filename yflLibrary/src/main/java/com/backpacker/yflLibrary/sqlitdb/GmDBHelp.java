package com.backpacker.yflLibrary.sqlitdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Author : YFL  is Creating a porject in xiaole
 * @Package cn.ruiye.xiaole.db
 * @Email : yufeilong92@163.com
 * @Time :2020/4/29 15:46
 * @Purpose :
 */
public class GmDBHelp extends SQLiteOpenHelper {

    public static final String TABLE_NAME="xiaole";
    public static final String DEFAULT_NAME="skmd.db";

    public static final String HISTORY_NAME = "history";
    public static final  String ORDER_LIST = "orderlist";
    public static final Integer DEFAULT_VERSION = 1;
    private static volatile GmDBHelp _singleton;

    public static GmDBHelp get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (GmDBHelp.class) {
                if (_singleton == null) {
                    _singleton = new GmDBHelp(context);
                }
            }
        }
        return _singleton;
    }
    public GmDBHelp(Context context) {
        super(context, DEFAULT_NAME,  null, DEFAULT_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String carInfom = "create table " + TABLE_NAME + " (" +
                "id integer primary key autoincrement," +
                "token text," + //token
                "userId text," + //用户id
                "phone text," + //手机号
                "name text," + //姓名
                "hear text," + //头像
                "signature text," + //个性
                "iscompany text," + //是否是企业
                "bindalipay text," + //是否绑定支付宝
                "bindsina text," + //是否绑定新浪
                "bindQQ text," + //是否绑定qq
                "bindwx text," + //是否绑定微信
                "extend text," +
                "extend_one text," +
                "extend_two text," +
                "extend_three text," +
                "extend_four text," +
                "extend_five text," +
                "extend_six text," +
                "extend_seven text," +
                "extend_eight text," +
                "extend_nine text," +
                "extend_ten text" +
                ");";
        db.execSQL(carInfom);
        String history = "create table " + HISTORY_NAME + " (" +
                "id integer primary key autoincrement," +
                "content text," +
                "extend_one text," +
                "extend_two text," +
                "extend_three text" +
                ");";
        db.execSQL(history);
        String orderList = "create table " + ORDER_LIST + " (" +
                "id integer primary key autoincrement," +
                "id_s text," +
                "parentId text," +
                "combineId text," +
                "description text," +
                "number text," +
                "showStatus text," +
                "productUnit text," +
                "productCount text," +
                "point text," +
                "name text," +
                "maxPrice text," +
                "minPrice text," +
                "icon text," +
                "displayIcon text," +
                "childrenType text," +
                "avgPrice text," +
                "attributeCount text," +
                "attributes text," +
                "chilerSelectAttribute text," +
                "integral text," +
                "price text," +
                "logoPath text" +
                ");";
        db.execSQL(orderList);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
