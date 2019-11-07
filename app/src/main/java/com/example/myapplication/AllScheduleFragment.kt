package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.model.ExpandableAdapter
import kotlinx.android.synthetic.main.expandable_schedule.*

class AllScheduleFragment : Fragment() {

    private var dbHandler: DatabaseHandler? = null

    val header : MutableList<String> = ArrayList()
    val body : MutableList<MutableList<String>> = ArrayList()

    companion object {
        fun newInstance() = AllScheduleFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.expandable_schedule, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        dbHandler = activity?.let { DatabaseHandler(it) }

        val monday : MutableList<String> = dbHandler!!.getSubjectByday(2)
        if (monday.size == 0) {
            monday.add("No class")
        }
        val tuesday : MutableList<String> = dbHandler!!.getSubjectByday(3)
        if (tuesday.size == 0) {
            tuesday.add("No class")
        }
        val wednesday : MutableList<String> = dbHandler!!.getSubjectByday(4)
        if (wednesday.size == 0) {
            wednesday.add("No class")
        }
        val thursday : MutableList<String> = dbHandler!!.getSubjectByday(5)
        if (thursday.size == 0) {
            thursday.add("No class")
        }
        val friday : MutableList<String> = dbHandler!!.getSubjectByday(6)
        if (friday.size == 0) {
            friday.add("No class")
        }
        val saturday : MutableList<String> = dbHandler!!.getSubjectByday(7)
        if (saturday.size == 0) {
            saturday.add("No class")
        }
        val sunday : MutableList<String> = dbHandler!!.getSubjectByday(1)
        if (sunday.size == 0) {
            sunday.add("No class")
        }

        header.add("Monday")
        header.add("Tuesday")
        header.add("Wednesday")
        header.add("Thursday")
        header.add("Friday")
        header.add("Saturday")
        header.add("Sunday")

        body.add(monday)
        body.add(tuesday)
        body.add(wednesday)
        body.add(thursday)
        body.add(friday)
        body.add(saturday)
        body.add(sunday)

        expandableListView.setAdapter(ExpandableAdapter(this.activity!!,expandableListView, header, body))
    }

}