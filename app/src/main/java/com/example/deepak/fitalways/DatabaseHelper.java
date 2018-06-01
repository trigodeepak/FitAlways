package com.example.deepak.fitalways;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Workout.db";
    public static final String TABLE_NAME = "workout_data";
    public static final String col1 = "id";
    public static final String col2 =  "date";
    public static final String col3 = "name_workout";
    public static final String col4 = "time",col5 = "calories",col6= "exerciseList";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"("+col1+" integer primary key autoincrement,"+col2+" date, "+col3+" varchar(50),"+col4+" long,"+col5+" integer,"+col6+" varchar(100));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(String name_workout, String date, int time, int calories, String exercise){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,date);
        contentValues.put(col3,name_workout);
        contentValues.put(col4,time);
        contentValues.put(col5,calories);
        contentValues.put(col6,exercise);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+TABLE_NAME + "order by date desc",null);
    }
}
