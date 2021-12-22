package id.praktikumprogmob.sewayupiks.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.praktikumprogmob.sewayupiks.model.User;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper (Context context) {
        super(context, "sewayu.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "phone_number TEXT, " +
                "email TEXT, " +
                "password TEXT," +
                "alamat TEXT," +
                "usia INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
    }

    public boolean addUser (User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", user.getName());
        cv.put("phone_number", user.getPhoneNumber());
        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword());
        cv.put("alamat", user.getAlamat());
        cv.put("usia", user.getUsia());

        return db.insert("user", null, cv) > 0;
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user",
                new String[]{"id", "name", "phone_number", "email", "password", "alamat", "usia"},
                "email" + "=?",
                new String[]{user.email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            User user1 = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6));
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user",
                new String[]{"id", "name", "phone_number", "email", "password", "alamat", "usia"},//Selecting columns want to query
                "email" + "=?",
                new String[]{email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }
}
