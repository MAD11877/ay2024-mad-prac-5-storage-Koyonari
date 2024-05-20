package sg.edu.np.mad.madpractical5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String USERS = "User";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_DESCRIPTION = "Description";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_FOLLOWED = "Followed";

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_ID + " TEXT,"
            + COLUMN_FOLLOWED + " INTEGER"
            + ")";

    private SQLiteDatabase db;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }

    public void insertUsers(List<User> users) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (User user : users) {
            db.execSQL("INSERT INTO " + USERS + " (" + COLUMN_NAME + ", " + COLUMN_DESCRIPTION + ", " + COLUMN_ID + ", " + COLUMN_FOLLOWED + ") VALUES ('" + user.getName() + "', '" + user.getDescription() + "', " + user.getId() + "', '" + (user.getFollowed() ? 1 : 0) + ")");
        }
    }
}