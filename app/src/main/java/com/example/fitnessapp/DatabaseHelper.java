package com.example.fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/*public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "databaseManager";
    private static final String TABLE_DATABASE = "databases";
    private static final String KEY_ID = "id";
    private static final String KEY_AGE = "age";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WEIGHT = "weight";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATABASE_TABLE = "CREATE TABLE " + TABLE_DATABASE+"("+KEY_ID + "INTEGER PRIMARY KEY," +KEY_AGE+"TEXT,"+ KEY_GENDER + "TEXT," + KEY_HEIGHT+"TEXT,"+KEY_WEIGHT+"TEXT"+")";
        db.execSQL(CREATE_DATABASE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "  + TABLE_DATABASE);
        onCreate(db);
    }

    void addDatabase(Person database){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, database.getId());
        values.put(KEY_AGE, database.getAge());
        values.put(KEY_GENDER, database.getGender());
        values.put(KEY_HEIGHT, database.getHeight());
        values.put(KEY_WEIGHT, database.getWeight());

        db.insert(TABLE_DATABASE,null,values);
        db.close();
    }

    Person getDatabase(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DATABASE,new String[] {KEY_ID,KEY_AGE, KEY_GENDER, KEY_HEIGHT, KEY_WEIGHT}, KEY_AGE+ "=?", new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        Person database = new Person(Integer.parseInt(cursor.getString(0)), cursor.getInt(1), cursor.getString(2),cursor.getInt(3), cursor.getInt(4));

        return database;
    }

    public List<Person> getAllDatabases(){
        List<Person> databaseList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_DATABASE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Person database = new Person();
                database.setId(Integer.parseInt(cursor.getString(0)));
                database.setAge(Integer.parseInt(cursor.getString(1)));
                database.setGender(cursor.getString(2));
                database.setHeight(Integer.parseInt(cursor.getString(3)));
                database.setWeight(Integer.parseInt(cursor.getString(4)));

                databaseList.add(database);
            }  while(cursor.moveToNext());
        }
        return databaseList;
    }

    public int updateDatabase(Person database){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, database.getId());
        values.put(KEY_AGE, database.getAge());
        values.put(KEY_GENDER, database.getGender());
        values.put(KEY_HEIGHT, database.getHeight());
        values.put(KEY_WEIGHT, database.getWeight());

        return db.update(TABLE_DATABASE, values, KEY_ID+"=?", new String[]{String.valueOf(database.getId())});
    }

    public void deleteDatabase(Person database){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATABASE,KEY_ID+"=?", new String[] {String.valueOf(database.getId())});
        db.close();
    }

    public int getDatabaseCount(){
        String countQuery = "SELECT * FROM " + TABLE_DATABASE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}*/
