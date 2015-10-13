package basti.coryphaei.com.mdtest.application;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import greendao.utils.DaoMaster;
import greendao.utils.DaoSession;
import greendao.utils.NoteDao;
import greendao.utils.SeqDao;

/**
 * Created by Bowen on 2015/10/12.
 */
public class App extends Application {

    public SQLiteDatabase db;
    public DaoMaster daoMaster;
    public DaoSession daoSession;
    public Cursor cursor;
    public static final String TAG = "DaoExample";
    public static final String[] Colors = {"#E0E0E0","#FF6D3F","#FF9700","#FFE900","#B8C4C9","#3FC3FF","#17E8B5","#8AC249"};
    @Override
    public void onCreate() {
        super.onCreate();

        // 官方推荐将获取 DaoMaster 对象的方法放到 Application 层，这样将避免多次创建生成 Session 对象
        setupDatabase();


    }

    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public NoteDao getNoteDao() {
        return daoSession.getNoteDao();
    }

    public SeqDao getOrderIdDao(){
        return daoSession.getSeqDao();
    }
}
