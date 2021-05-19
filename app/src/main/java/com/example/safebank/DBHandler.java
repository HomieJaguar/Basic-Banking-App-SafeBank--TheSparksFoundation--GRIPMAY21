package com.example.safebank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DBHandler(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbh) {
        dbh.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        dbh.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        dbh.execSQL("insert into user_table values(1234567890,'Abuzar',10000.00,'abuzar@gmail.com','XXXXXXXXXXXX1234','GSGX09876543')");
        dbh.execSQL("insert into user_table values(2468101214,'Adarsh',7000.20,'adarsh@gmail.com','XXXXXXXXXXXX4647','BCA98765432')");
        dbh.execSQL("insert into user_table values(3691215182,'Ashmit',5000.89,'ashmit@gmail.com','XXXXXXXXXXXX2345','CAB87654321')");
        dbh.execSQL("insert into user_table values(4812162024,'Alisha',5000.95,'alisha@gmail.com','XXXXXXXXXXXX4563','ABC76543210')");
        dbh.execSQL("insert into user_table values(5101520253,'Harvey',4000.42,'harvey@gmail.com','XXXXXXXXXXXX3247','BCA65432109')");
        dbh.execSQL("insert into user_table values(6121824303,'Jessica',4500.64,'jessica@gmail.com','XXXXXXXXXXXX8796','CAB54321098')");
        dbh.execSQL("insert into user_table values(7142128354,'Mike',100.00,'mike@gmail.com','XXXXXXXXXXXX2347','ABC43210987')");
        dbh.execSQL("insert into user_table values(8162432404,'Rachel',235.54,'rachel@gmail.com','XXXXXXXXXXXX6755','BCA32109876')");
        dbh.execSQL("insert into user_table values(9182736455,'Chandler',981.12,'chandler@gmail.com','XXXXXXXXXXXX9874','CAB21098765')");
        dbh.execSQL("insert into user_table values(1020304050,'Sarthak',8000.23,'sarthak@gmail.com','XXXXXXXXXXXX5673','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}

