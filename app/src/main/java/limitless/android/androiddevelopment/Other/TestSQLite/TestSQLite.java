package limitless.android.androiddevelopment.Other.TestSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class TestSQLite extends SQLiteOpenHelper {

    private static String name = "test.sqlite";
    private static int version = 1;

    private String tName = "users";
    private String userId = "uuid";
    private String userName = "name";
    private String userDescription = "description";
    private String userJob = "job";
    private String userTableCode = "CREATE TABLE IF NOT EXISTS " + tName + "(" +
            userId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            userName + " TEXT, " +
            userDescription + " TEXT, " +
            userJob + " TEXT);";
    private SQLiteDatabase db;

    public TestSQLite(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(userTableCode);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void  read(){
        db = getReadableDatabase();
    }

    private void write(){
        db = getWritableDatabase();
    }

    public List<UserTest> getAllUsers(){
        read();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tName, null);
        if (cursor == null)
            return null;
        if (cursor.getCount() <= 0 || ! cursor.moveToFirst()){
            cursor.close();
            return null;
        }
        int id = cursor.getColumnIndex(userId);
        int name = cursor.getColumnIndex(userName);
        int des = cursor.getColumnIndex(userDescription);;
        int job = cursor.getColumnIndex(userJob);
        List<UserTest> users = new ArrayList<>();
        do {
            users.add(new UserTest(
                    cursor.getInt(id),
                    cursor.getString(name),
                    cursor.getString(des),
                    cursor.getString(job)
            ));
        }while (cursor.moveToNext());
        cursor.close();
        return users;
    }

    public UserTest getUser(String name){
        read();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tName + " WHERE " + userName + " LIKE '" + name + "'", null);
        if (cursor == null)
            return null;
        if (cursor.getCount() <= 0 || ! cursor.moveToFirst()){
            cursor.close();
            return null;
        }
        UserTest us = new UserTest();
        us.uuid = cursor.getInt(cursor.getColumnIndex(userId));
        us.name = cursor.getString(cursor.getColumnIndex(userName));
        us.description = cursor.getString(cursor.getColumnIndex(userDescription));
        us.job = cursor.getString(cursor.getColumnIndex(userJob));
        cursor.close();
        return us;
    }

    public boolean insertUser(UserTest user){
        write();
        ContentValues cv = new ContentValues();
        cv.put(userName, user.name);
        cv.put(userDescription, user.description);
        cv.put(userJob, user.job);
        return db.insert(tName, null, cv) > 0;
    }

    public boolean deleteUser(String name){
        write();
        return db.delete(tName, userName + " LIKE ?", new String[]{name}) > 0;
    }


}

