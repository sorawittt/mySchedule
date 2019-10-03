package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler (context: Context) :
    SQLiteOpenHelper(context, "ScheduleDB", null, 3) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE Schedule " +
                "(id Integer PRIMARY KEY AUTOINCREMENT, classroom TEXT, name TEXT, start_time TEXT, end_time TEXT, day Text)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun addSubject(name:String, classroom:String, start_time:String, end_time:String, day:String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("classroom", classroom)
        values.put("start_time", start_time)
        values.put("end_time", end_time)
        values.put("day", day)
        val success = db.insert("Schedule", null, values)
        db.close()
        Log.v("InsertedID", "$success")
        return (Integer.parseInt("$success") != -1)
    }

    fun getSubject() : Array<String> {
        val db = readableDatabase
        var name = ""
        var classroom = ""
        var startTime = ""
        var endTime = ""
        val selectQuery = "SELECT * FROM Schedule WHERE day = 4"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex("name"))
                classroom = cursor.getString(cursor.getColumnIndex("classroom"))
                startTime = cursor.getString(cursor.getColumnIndex("start_time"))
                endTime = cursor.getString(cursor.getColumnIndex("end_time"))
            }
        }
        return arrayOf(name, classroom, startTime, endTime)
    }


}