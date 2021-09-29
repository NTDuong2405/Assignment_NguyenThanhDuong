package com.example.asignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="EXPENSES";
    public static final int DB_VERSION = 1;

    public  static  String TABLE_NAME_EXPENSES = "TBL_EXPENSES";
    public  static  String TABLE_NAME_CATEGORIES = "TBL_CATEGORIES";

    public  static  String ID="_id";
    public  static String NAME="name";
    public  static  String DESCRIPTION="description";
    public  static String INFO="info";
    public  static String PRICE="price";
    public  static String DATE="date";
    public static String CATEGORIES="categories";

    public DBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME_EXPENSES + "(" +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " +
                DESCRIPTION + " TEXT," +
                INFO + " TEXT," +
                PRICE + "TEXT," +
                DATE + "TEXT)";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE " + TABLE_NAME_CATEGORIES +"( "
                + ID + " INTEGER PRIMARY KEY, "
                + CATEGORIES + " TEXT)";
        db.execSQL(sql2);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME_EXPENSES;
        db.execSQL(sql);
        onCreate(db);
    }

    public String addExpense(String expenseName,String des,String info,String price, String date){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,expenseName);
        contentValues.put(DESCRIPTION,des);
        contentValues.put(INFO,info);
        contentValues.put(PRICE,price);
        contentValues.put(DATE,date);
        long isAdd = db.insert(TABLE_NAME_EXPENSES,null,contentValues);
        if(isAdd == -1){
            return "Add Fail";
        }
        db.close();
        return "Add success";
    }

    public  String updateExpense(int id, String expenseName,String des,String info,String price, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,expenseName);
        contentValues.put(DESCRIPTION,des);
        contentValues.put(INFO,info);
        contentValues.put(PRICE,price);
        contentValues.put(DATE,date);
        int isUpdate = db.update(TABLE_NAME_EXPENSES,contentValues, ID+" = ? ", new String[] {id+""});
        if(isUpdate > 0){
            return "Update success";
        }
        db.close();
        return "Update Fail";
    }

    public String deleteExpense(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int isDelete = db.delete(TABLE_NAME_EXPENSES,ID+ " = ?",new String[]{id+""});
        if(isDelete > 0){
            return  "Delete success";
        }
        db.close();
        return  "Delete Fail";
    }

    public Cursor getAllExpense(){
        SQLiteDatabase db  = this.getReadableDatabase();
        String sql="SELECT * FROM " + TABLE_NAME_EXPENSES;
        Cursor c = db.rawQuery(sql,null);
        return c;
    }
    public Cursor getAllCategory() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME_CATEGORIES;
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public void addCategory(String name, String gender, String desc) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", 1);
        contentValues.put("categoryName", "shopping");
        db.insert("Category", null, contentValues);

        ContentValues contentValues2 = new ContentValues();
        contentValues.put("id", 2);
        contentValues.put("categoryName", "home");
        db.insert("Category", null, contentValues2);

        ContentValues contentValues3 = new ContentValues();
        contentValues.put("id", 3);
        contentValues.put("categoryName", "food");
        db.insert("Category", null, contentValues3);
    }



}


