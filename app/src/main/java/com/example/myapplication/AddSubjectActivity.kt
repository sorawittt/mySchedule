package com.example.myapplication

import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_add_subject.*
import java.util.*


class AddSubjectActivity : AppCompatActivity() {
    var sunday = "0"
    var monday = "0"
    var tuesday = "0"
    var wednesday = "0"
    var thursday = "0"
    var friday = "0"
    var saturday = "0"
    var startTime = ""
    var endTime = ""
    var dbHandler: DatabaseHandler? = null
    var gName = ""
    var gRoom = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)
        setSupportActionBar(toolbar)
        val name = findViewById<EditText>(R.id.name_edit_text)
        val room = findViewById<EditText>(R.id.room_edit_text)
        dbHandler = DatabaseHandler(this)

        sundayButton.setOnClickListener {
            if (sunday == "0") {
                sundayButton.setBackgroundColor(Color.parseColor("#263238"))
                sundayButton.setTextColor(Color.WHITE)
                sunday = "1"
            } else {
                sundayButton.setBackgroundColor(Color.parseColor("#FAFAFA"))
                sundayButton.setTextColor(Color.RED)
                sunday = "0"

            }
            Log.e("sunday", sunday)
        }

        mondayButton.setOnClickListener {
            if (monday == "0") {
                mondayButton.setBackgroundColor(Color.parseColor("#263238"))
                mondayButton.setTextColor(Color.WHITE)
                monday = "2"
            } else {
                mondayButton.setBackgroundColor(Color.parseColor("#FAFAFA"))
                mondayButton.setTextColor(Color.BLACK)
                monday = "0"
            }
        }

        tuesdayButton.setOnClickListener {
            if (tuesday == "0") {
                tuesdayButton.setBackgroundColor(Color.parseColor("#263238"))
                tuesdayButton.setTextColor(Color.WHITE)
                tuesday = "3"
            } else {
                tuesdayButton.setBackgroundColor(Color.parseColor("#FAFAFA"))
                tuesdayButton.setTextColor(Color.BLACK)
                tuesday = "0"
            }
        }

        wednesdayButton.setOnClickListener {
            if (wednesday == "0") {
                wednesdayButton.setBackgroundColor(Color.parseColor("#263238"))
                wednesdayButton.setTextColor(Color.WHITE)
                wednesday = "4"
            } else {
                wednesdayButton.setBackgroundColor(Color.parseColor("#FAFAFA"))
                wednesdayButton.setTextColor(Color.BLACK)
                wednesday = "0"
            }
        }

        thursdayButton.setOnClickListener {
            if (thursday == "0") {
                thursdayButton.setBackgroundColor(Color.parseColor("#263238"))
                thursdayButton.setTextColor(Color.WHITE)
                thursday = "5"
            } else {
                thursdayButton.setBackgroundColor(Color.parseColor("#FAFAFA"))
                thursdayButton.setTextColor(Color.BLACK)
                thursday = "0"
            }
        }

        fridayButton.setOnClickListener {
            if (friday == "0") {
                fridayButton.setBackgroundColor(Color.parseColor("#263238"))
                fridayButton.setTextColor(Color.WHITE)
                friday = "6"
            } else {
                fridayButton.setBackgroundColor(Color.parseColor("#FAFAFA"))
                fridayButton.setTextColor(Color.BLACK)
                friday = "0"
            }
        }

        saturdayButton.setOnClickListener {
            if (saturday == "0") {
                saturdayButton.setBackgroundColor(Color.parseColor("#263238"))
                saturdayButton.setTextColor(Color.WHITE)
                saturday = "7"
            } else {
                saturdayButton.setBackgroundColor(Color.parseColor("#FAFAFA"))
                saturdayButton.setTextColor(Color.BLACK)
                saturday = "0"
            }
        }

        start_time.setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR) + 12
            val minute = c.get(Calendar.MINUTE)

            val tpd = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener(function = { _, h, m ->

                    Toast.makeText(this, "$h : $m", Toast.LENGTH_LONG).show()
                    var hour = h.toString()
                    var minute = m.toString()
                    if (h < 10) {
                        hour = "0$h"
                    }
                    if (m < 10) {
                        minute = "0$m"
                    }

                    start_time.text = "$hour : $minute"
                    startTime = "$hour:$minute"

                }),hour,minute,true)

            tpd.show()
        }

        end_time.setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR) + 12
            val minute = c.get(Calendar.MINUTE)

            val tpd = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener(function = { _, h, m ->

                    Toast.makeText(this, "$h : $m", Toast.LENGTH_LONG).show()
                    var hour = h.toString()
                    var minute = m.toString()
                    if (h < 10) {
                        hour = "0$h"
                    }
                    if (m < 10) {
                        minute = "0$m"
                    }

                    end_time.text = "$hour : $minute"
                    endTime = "$hour:$minute"

                }),hour,minute,true)

            tpd.show()
        }

        saveButton.setOnClickListener {
            gName = name.text.toString()
            gRoom = room.text.toString()
            if (!check()) {
                val days = arrayOf(sunday, monday, tuesday, wednesday, thursday, friday, saturday)
                for (day in days) {
                    if (day != "0") {
                        val success: Boolean = dbHandler!!.addSubject(
                            name_edit_text.text.toString(),
                            room_edit_text.text.toString(),
                            startTime,
                            endTime,
                            day
                        )
                        if (success) {
                            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_LONG).show()
                        }

                    }
                }
                Log.e("day", days.contentToString())
                finish()
                startActivity(intent)
            }
        }


    }

    private fun check(): Boolean {
        var error = false

        when {
            gName.trim().isEmpty() -> {
                Toast.makeText(this, "Please input subject name", Toast.LENGTH_LONG).show()
                error = true
            }
            gRoom.trim().isEmpty() -> {
                Toast.makeText(this, "Please input room", Toast.LENGTH_LONG).show()
                error = true
            }
            startTime.isEmpty() -> {
                Toast.makeText(this, "Please input start time", Toast.LENGTH_LONG).show()
                error = true
            }
            endTime.isEmpty() -> {
                Toast.makeText(this, "Please input end time", Toast.LENGTH_LONG).show()
                error = true
            }
        }

        return error
    }

}

