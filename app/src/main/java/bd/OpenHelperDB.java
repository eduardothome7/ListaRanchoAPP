package bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eduardo on 25/05/2017.
 */

public class OpenHelperDB extends SQLiteOpenHelper {
    private static String dbname = "rancho.bd";
    private static String createTable = "CREATE TABLE itens" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name VARCHAR(30)," +
            "description VARCHAR(200)," +
            "price decimal(4,2)," +
            "quantity INTEGER" +
            ")";

    public OpenHelperDB(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE itens");
        db.execSQL(createTable);
    }
}
