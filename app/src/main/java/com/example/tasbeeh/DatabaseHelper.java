package com.example.tasbeeh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "Tasbeeh_DB";
    private static final int DATABASE_VERSION = 1;

    // Table Name
    private static final String TABLE_REGISTER = "register";

    // Table Columns
    private static final String COL_ID = "_id";
    private static final String COL_USERNAME = "username";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_MOBILE = "mobile";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REGISTER_TABLE = "CREATE TABLE " + TABLE_REGISTER + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_USERNAME + " TEXT, "
                + COL_EMAIL + " TEXT, "
                + COL_PASSWORD + " TEXT, "
                + COL_MOBILE + " TEXT" + ")";
        db.execSQL(CREATE_REGISTER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);
        onCreate(db);
    }

    public boolean insertUser(String username, String email, String password, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_MOBILE, mobile);
        long result = db.insert(TABLE_REGISTER, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean checkUserByUsername(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_ID};
        String selection = COL_USERNAME + " = ?" + " AND " + COL_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_REGISTER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
}