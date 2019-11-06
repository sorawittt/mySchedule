package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.model.ScheduleAdapter
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ScheduleFragment : Fragment() {
    private var dbHandler: DatabaseHandler? = null
    private var root: View? = null
    val calendar = Calendar.getInstance()
    val day = calendar.get(Calendar.DAY_OF_WEEK)
    @RequiresApi(Build.VERSION_CODES.O)
    val current = LocalDateTime.now()
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    @RequiresApi(Build.VERSION_CODES.O)
    val formatted = current.format(formatter)

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_schedule, container,
            false)
        try {
            dbHandler = activity?.let { DatabaseHandler(it) }
            val subject = dbHandler!!.getSubject(day, formatted)[0]

            Log.e("2", dbHandler!!.getSubject(day, formatted).toString())
            Log.e("Now", formatted)
            Log.e("day", day.toString())

            Log.e("database", subject.name + " " +  subject.classroom + " " + subject.startTime + " " + subject.endTime)
        } catch (e:Exception) {
            root = inflater.inflate(R.layout.fragment_schedule_empty, container,
                false)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectList = dbHandler!!.getSubject(day, formatted)
        val subjectAdapter = ScheduleAdapter(subjectList)

        try {
            recycler_schedule.apply {
                recycler_schedule.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                isNestedScrollingEnabled = false
                adapter = subjectAdapter
                onFlingListener = null
            }
        } catch (e:Exception) {}


        subjectAdapter.notifyDataSetChanged()


    }
}