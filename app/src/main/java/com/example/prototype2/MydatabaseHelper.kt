package com.example.prototype2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log

class MydatabaseHelper(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION){

    companion object {
        private const val DATABASE_NAME = "H_CRM.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "User_Library"
        private const val USER_ID = "USER_ID"
        private const val USER_NAME = "USER_NAME"
        private const val USER_EMAIL = "USER_EMAIL"
        private const val USER_PHONE_NUMBER = "USER_PHONE_NUMBER"
        private const val USER_GENDER = "USER_GENDER"
        private const val USER_BDATE = "USER_BDATE"
        private const val USER_PWD = "USER_PWD"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE " + TABLE_NAME + " (" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT, " +
                USER_EMAIL + " TEXT, " +
                USER_PHONE_NUMBER + " TEXT, " +
                USER_GENDER + " TEXT, " +
                USER_BDATE + " TEXT, " +
                USER_PWD + " TEXT); "
        db!!.execSQL(query)
        Log.d("Queary Sting", query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("drop table if exists  $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(Account: Account){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(USER_NAME,Account.user_name)
            put(USER_EMAIL,Account.user_email)
            put(USER_PHONE_NUMBER,Account.USER_PHONE_NUMBER)
            put(USER_GENDER,Account.USER_GENDER)
            put(USER_BDATE,Account.USER_BDATE)
            put(USER_PWD,Account.USER_PWD)
        }
        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    fun printData() {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        try {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME))
                    val EMAIL = cursor.getString(cursor.getColumnIndexOrThrow(USER_EMAIL))
                    val PHONE_NUMBER = cursor.getString(cursor.getColumnIndexOrThrow(USER_PHONE_NUMBER))
                    val GENDER = cursor.getString(cursor.getColumnIndexOrThrow(USER_GENDER))
                    val BDATE = cursor.getString(cursor.getColumnIndexOrThrow(USER_BDATE))
                    val PASSWORD = cursor.getString(cursor.getColumnIndexOrThrow(USER_PWD))
//                    val account = Account(id, name, EMAIL, PHONE_NUMBER, GENDER, BDATE, PASSWORD)
                    // Print the data to Logcat
                    Log.d(
                        "SQL Query Test",
                        "ID: $id, Name: $name, EMAIL: $EMAIL, PASSWORD: $PASSWORD, PHONE_NUMBER: $PHONE_NUMBER, GENDER: $GENDER, BDATE: $BDATE"
                    )

                } while (cursor.moveToNext())
            }
        } finally {
            cursor.close()
            db.close()
        }
    }
}