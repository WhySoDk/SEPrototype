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

        //Appointment table detail
        private const val APPOINTMENT_TABLE_NAME = "APPOINTMENT_Library"
        private const val APPOINTMENT_ID = "APPOINTMENT_ID"
        private const val APPOINTMENT_User_NAME = "APPOINTMENT_User_ID"
        private const val APPOINTMENT_Date = "APPOINTMENT_Date"

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
        Log.d("Query User Sting", query)
        val query2 = "CREATE TABLE " + APPOINTMENT_TABLE_NAME + " ("+
                APPOINTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                APPOINTMENT_User_NAME + " TEXT, " +
                APPOINTMENT_Date + " TEXT); "
        db!!.execSQL(query2)
        Log.d("Query Appointment Sting", query2)
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

    fun getUsername(Email: String): String{
        val db = writableDatabase
        val selection = "$USER_EMAIL = ?"
        var Username =""
        val cursor = db.query(
            "$TABLE_NAME",
            null,
            selection,
            arrayOf(Email),
            null,
            null,
            null
        )

        try {
            if (cursor.count == 0){
                Log.d(
                    "getUsername Query Test",
                    "No data"
                )
            }else {
                if (cursor.moveToFirst()){
                    Username = cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME))
                    Log.d(
                        "getAppointmentDate Query Test",
                        "Username: $Username"
                    )
                }
            }
        }finally {
            cursor.close()
            db.close()
        }
        return Username
    }

    fun getAppointmentDate(Username: String): List<String> {
        val db = writableDatabase
        val selection = "$APPOINTMENT_User_NAME = ?"
        val AppointmentList = mutableListOf<String>()
        val cursor = db.query(
            "$APPOINTMENT_TABLE_NAME",
            null,
            selection,
            arrayOf(Username),
            null,
            null,
            null
        )

        try {
            if (cursor.count == 0) {
                Log.d(
                    "getAppointmentDate Query Test",
                    "No data"
                )
            } else {
                if (cursor.moveToFirst()) {
                    do {
                        val date = cursor.getString(cursor.getColumnIndexOrThrow(APPOINTMENT_Date))
                        Log.d(
                            "getAppointmentDate Query Test",
                            "date: $date"
                        )
                        AppointmentList.add(date)
                    } while (cursor.moveToNext())
                }
            }
        } finally {
            cursor.close()
            db.close()
        }

        return AppointmentList
    }

    fun insertAppointment(Username: String,Date: String){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(APPOINTMENT_User_NAME,Username)
            put(APPOINTMENT_Date,Date)
        }
        db.insert(APPOINTMENT_TABLE_NAME,null,values)
        db.close()
    }

    fun UpdateAppointment(Username: String,Olddate: String,Newdate: String){
        val db = writableDatabase
        val selection = "$APPOINTMENT_User_NAME = ? AND $APPOINTMENT_Date =?"
        val selectionArgs = arrayOf(Username, Olddate)
        val values = ContentValues().apply {
            put(APPOINTMENT_Date,Newdate)
        }
        db.update(APPOINTMENT_TABLE_NAME,values,selection,selectionArgs)
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


    fun isUserCredentialsValid(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val selection = "$USER_EMAIL = ? AND $USER_PWD = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor = db.query("$TABLE_NAME", null, selection, selectionArgs, null, null, null)

        val isValid = cursor.count > 0

        cursor.close()
        db.close()

        return isValid
    }

}