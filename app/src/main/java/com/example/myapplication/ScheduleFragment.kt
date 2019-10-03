package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*

class ScheduleFragment : Fragment() {
    private var dbHandler: DatabaseHandler? = null
    private var root: View? = null

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_schedule, container,
            false)
        dbHandler = activity?.let { DatabaseHandler(it) }
        val subject = dbHandler!!.getSubject()

        Log.e("database", subject[0] + " " +  subject[1] + " " + subject[2] + " " + subject[3])
        if (subject[0] != "") {
            root!!.subject_name.text = subject[0]
            root!!.classroom.text = subject[1]
            root!!.start_and_end_time.text = subject[2] + " - " + subject[3]
        } else {
            root!!.subject_name.visibility = View.INVISIBLE
            root!!.classroom.visibility = View.INVISIBLE
            root!!.start_and_end_time.visibility = View.INVISIBLE
            root!!.ad.visibility = View.INVISIBLE
            root!!.no_study.visibility = View.VISIBLE
        }
        return root
    }
}