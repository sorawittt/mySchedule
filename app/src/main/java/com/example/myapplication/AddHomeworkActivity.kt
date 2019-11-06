package com.example.myapplication

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_homework.*
import kotlinx.android.synthetic.main.activity_add_homework.saveButton
import kotlinx.android.synthetic.main.activity_add_subject.*

class AddHomeworkActivity : AppCompatActivity() {
    var due_date = ""
    var dbHandler: DatabaseHandler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_homework)
        dbHandler = DatabaseHandler(this)


//        val allSubjects = dbHandler!!.getSubjectNameAndId()

        var nameSubject = arrayOf<String>()



        auto_complete_text_view.onItemClickListener = AdapterView.OnItemClickListener{
                parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            // Display the clicked item using toast
            Toast.makeText(applicationContext,"Selected : $selectedItem",Toast.LENGTH_SHORT).show()
        }


        dueDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                // Display Selected date in Toast
                Toast.makeText(this, """$dayOfMonth - ${monthOfYear + 1} - $year""", Toast.LENGTH_LONG).show()
                dueDate.text = """$dayOfMonth / ${monthOfYear + 1} / $year"""
                due_date = """$dayOfMonth/${monthOfYear + 1}/$year"""
            }, year, month, day)
            dpd.show()
        }

        saveButton.setOnClickListener {

        }
    }
}