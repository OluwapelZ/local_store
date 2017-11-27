package com.example.oluwapelumiolaoye.local_store.pet.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by oluwapelumi.olaoye on 11/19/17.
 */

public class SqliteDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "user_details";
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLOUMN_ID = "_id";
    public static final String USER_COLOUMN_NAME = "name";
    public static final String USER_COLOUMN_AGE = "age";
    public static final String USER_COLOUMN_GENDER = "gender";

    public SqliteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating the sql database.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + USER_TABLE_NAME + " (" +
                USER_COLOUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COLOUMN_NAME + "TEXT, " +
                USER_COLOUMN_AGE + " INT, " +
                USER_COLOUMN_GENDER + " TEXT" + ")"
        );
    }

    //Upgrades the version of the Database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
