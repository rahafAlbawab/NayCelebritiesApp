package com.rahafmaria.naycelebrities.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class LocalDB extends SQLiteOpenHelper {
    public static final String DB_NAME = "Nay_Database";
    public static final int DB_VERSION = 1;
    public static final String CHAT = "Chat";

    public LocalDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + CHAT +
                "(chat_id integer , user_sender_id integer , user_receiver_id integer " +
                ", message varchar(255) , message_send_date timestamp ,type integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + CHAT);
        onCreate(sqLiteDatabase);
    }

    public boolean insertToTableNayDB(int user_sender_id, int user_receiver_id, String message, int type) {
        final String chat_id = Math.min(user_sender_id, user_receiver_id) + "" + Math.max(user_sender_id, user_receiver_id);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("chat_id", chat_id);
        contentValues.put("user_sender_id", user_sender_id);
        contentValues.put("user_receiver_id", user_receiver_id);
        contentValues.put("message", message);
        contentValues.put("type", type);
        long result = db.insert(CHAT, null, contentValues);
        Log.d("hi", "hi");
        return result != -1;

    }
}
