package com.example.edgarpetrosian.getcontact.Engine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.example.edgarpetrosian.getcontact.Engine.Constants.DB_NAME;
import static com.example.edgarpetrosian.getcontact.Engine.Constants.DB_TABLE;
import static com.example.edgarpetrosian.getcontact.Engine.Constants.DB_VERSION;
import static com.example.edgarpetrosian.getcontact.Engine.Constants.ID;
import static com.example.edgarpetrosian.getcontact.Engine.Constants.IMAGE;
import static com.example.edgarpetrosian.getcontact.Engine.Constants.NAME;
import static com.example.edgarpetrosian.getcontact.Engine.Constants.NUMBER;

public class Services extends SQLiteOpenHelper {

    public Services(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT NOT NULL, " +
                NUMBER + " TEXT NOT NULL, " +
                IMAGE + " TEXT NOT NULL); "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        this.onCreate(db);
    }

    public void save(ModelGetContacts model) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

            cv.put(NAME, model.getName());
            cv.put(NUMBER, model.getNumber());
            cv.put(IMAGE, model.getUri());
            db.insert(DB_TABLE, null, cv);
            db.close();



    }


    public List<ModelGetContacts> getAllinform(String filter) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "";
        if (filter.equals("")) {
            query = "SELECT  * FROM " + DB_TABLE;
        }
        List<ModelGetContacts> list = new LinkedList<>();
        Cursor cursor = db.rawQuery(query, null);
        ModelGetContacts model;
        if (cursor.moveToFirst()) {
            do {
                model = new ModelGetContacts();
                model.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                model.setNumber(cursor.getString(cursor.getColumnIndex(NUMBER)));
                model.setUri(cursor.getString(cursor.getColumnIndex(IMAGE)));
                list.add(model);
            } while (cursor.moveToNext());
        }
        return list;
    }

}
