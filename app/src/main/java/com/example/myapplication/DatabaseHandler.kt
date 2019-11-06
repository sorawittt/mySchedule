package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.myapplication.model.Subject
import java.lang.reflect.Array

class DatabaseHandler (context: Context) :
    SQLiteOpenHelper(context, "ScheduleDB", null, 3) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE Schedule " +
                "(id Integer PRIMARY KEY AUTOINCREMENT, classroom TEXT, name TEXT, start_time time, end_time time, day Text)"
        db?.execSQL(createTable)
        val createHomework = "CREATE TABLE Homework " +
                "(id Integer PRIMARY KEY AUTOINCREMENT, name TEXT, detail TEXT, due_date TEXT, schedule_id Integer, FOREIGN KEY (schedule_id) REFERENCES Schedule(id))"
        db?.execSQL(createHomework)
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
        Log.e("InsertedID", "$success")
        return (Integer.parseInt("$success") != -1)
    }

    fun getSubject(today:Int, currentTime:String) : ArrayList<Subject> {
        val db = readableDatabase
        val subjects = arrayListOf<Subject>()
        var id: Int
        var name: String
        var classroom: String
        var startTime: String
        var endTime: String
        var day: String
        val selectQuery = "SELECT * FROM Schedule WHERE (day=$today AND (datetime(start_time) >= datetime('$currentTime') OR datetime('$currentTime') <= datetime(end_time))) ORDER BY datetime(start_time)"
        val cursor = db.rawQuery(selectQuery, null)
        while (cursor.moveToNext()) {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                classroom = cursor.getString(cursor.getColumnIndex("classroom"))
                startTime = cursor.getString(cursor.getColumnIndex("start_time"))
                endTime = cursor.getString(cursor.getColumnIndex("end_time"))
                day = cursor.getString(cursor.getColumnIndex("day"))
                subjects.add(Subject(id, classroom, name, startTime, endTime, day))
        }
        return subjects
    }

    fun getSubjectByday(today:Int) : ArrayList<String> {
        val db = readableDatabase
        val subjects = arrayListOf<String>()
        var id: Int
        var name: String
        var classroom: String
        var startTime: String
        var endTime: String
        var day: String
        val selectQuery = "SELECT * FROM Schedule WHERE day=$today ORDER BY datetime(start_time)"
        val cursor = db.rawQuery(selectQuery, null)
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("id"))
            name = cursor.getString(cursor.getColumnIndex("name"))
            classroom = cursor.getString(cursor.getColumnIndex("classroom"))
            startTime = cursor.getString(cursor.getColumnIndex("start_time"))
            endTime = cursor.getString(cursor.getColumnIndex("end_time"))
            day = cursor.getString(cursor.getColumnIndex("day"))
            var temp = "$name @ $classroom $startTime - $endTime"
            subjects.add(temp)
        }
        return subjects
    }

//    fun getSubjectNameAndId() : ArrayList<Subject> {
//        val db = readableDatabase
//        var id = ""
//        val checkDuplicate = arrayListOf<String>()
//        val subjects = arrayListOf<Subject>()
//        val selectQuery = "SELECT id, name FROM Schedule"
//        val cursor = db.rawQuery(selectQuery, null)
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                id = cursor.getString(cursor.getColumnIndex("id"))
//                if (id !in checkDuplicate) {
//                    checkDuplicate.add(id)
//                    subjects.add(Subject(cursor.getString(cursor.getColumnIndex("id")), cursor.getColumnIndex("id")))
//                }
//            }
//        }
//        return subjects
//
//    }
//
//    fun addHomework(name:String, due_date:String, detail:String) {}


}