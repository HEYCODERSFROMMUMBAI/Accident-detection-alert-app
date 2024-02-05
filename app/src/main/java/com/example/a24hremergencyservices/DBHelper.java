package com.example.a24hremergencyservices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper {

    private Context con;
    private SQLiteDatabase db;

    public DBHelper(Context con) {this.con = con;}

    public DBHelper OpenDB(){
        DBConnector dbCon = new DBConnector(con);
        db=dbCon.getWritableDatabase();
        return this;
    }

    public boolean RegisterCleaner(Cleaner cleaner){
        try {
            ContentValues cv =new ContentValues();
            cv.put("UserName", cleaner.getUserName());
            cv.put("Tel", cleaner.getTel());
            cv.put("Email",cleaner.getEmail());
            cv.put("Password", cleaner.getPassword());

            db.insert("cleanerInfo",null,cv);
            return true;
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public ArrayList<Cleaner> LoginCleaner (String UserName, String Password){
        ArrayList<Cleaner> userList = new ArrayList<Cleaner>();
        try{
            Cursor cursor = db.rawQuery("Select * from cleanerInfo where UserName= '"+UserName+"'and Password='"+Password+"'", null);
            if (cursor.moveToFirst())
            {
                do{
                    Cleaner cleaner = new Cleaner();
                    cleaner.setUserName(cursor.getString( 0));
                    cleaner.setPassword(cursor.getString( 1));
                    userList.add(cleaner);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return userList;
    }
}