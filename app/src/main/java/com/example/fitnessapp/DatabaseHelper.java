package com.example.fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "databaseManager";
    private static final String TABLE_DATABASE = "databases";
    private static final String KEY_AGE = "age";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WEIGHT = "weight";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATABASE_TABLE = "CREATE TABLE " + TABLE_DATABASE+"("+KEY_AGE + "INTEGER PRIMARY KEY," + KEY_GENDER + "TEXT," + KEY_HEIGHT+"TEXT,"+KEY_WEIGHT+"TEXT"+")";
        db.execSQL(CREATE_DATABASE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "  + TABLE_DATABASE);
        onCreate(db);
    }

    void addDatabase(Database database){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AGE, database.getAge());
        values.put(KEY_GENDER, database.getGender());
        values.put(KEY_HEIGHT, database.getHeight());
        values.put(KEY_WEIGHT, database.getWeight());

        db.insert(TABLE_DATABASE,null,values);
        db.close();
    }

    Database getDatabase(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DATABASE,new String[] {KEY_AGE, KEY_GENDER, KEY_HEIGHT, KEY_WEIGHT}, KEY_AGE+ "=?", new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        Database database = new Database(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getInt(2), cursor.getInt(3));

        return database;
    }

    public List<Database> getAllDatabases(){
        List<Database> databaseList = new ArrayList<>();

        String selectQuery = "SELECT *FROM " + TABLE_DATABASE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Database database = new Database();
                database.setAge(Integer.parseInt(cursor.getString(0)));
                database.setGender(cursor.getString(1));
                database.setHeight(Integer.parseInt(cursor.getString(2)));
                database.setWeight(Integer.parseInt(cursor.getString(3)));

                databaseList.add(database);
            }  while(cursor.moveToNext());
        }
        return databaseList;
    }

    public int updateDatabase(Database database){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AGE, database.getAge());
        values.put(KEY_GENDER, database.getGender());
        values.put(KEY_HEIGHT, database.getHeight());
        values.put(KEY_WEIGHT, database.getWeight());

        return db.update(TABLE_DATABASE, values, KEY_AGE+"=?", new String[]{String.valueOf(database.getAge())});
    }

    public void deleteDatabase(Database database){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATABASE,KEY_AGE+"=?", new String[] {String.valueOf(database.getAge())});
        db.close();
    }

    public int getDatabaseCount(){
        String countQuery = "SELECT *FROM " + TABLE_DATABASE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
