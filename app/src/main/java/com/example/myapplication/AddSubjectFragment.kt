package com.example.myapplication

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.add_subject_fragment.*
import java.util.*

class AddSubjectFragment : Fragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance() : AddSubjectFragment {
            return AddSubjectFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.add_subject_fragment, container, false)

        val btn = view.findViewById(R.id.start_time) as Button
        btn.setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR)
            val minute = c.get(Calendar.MINUTE)

            val tpd = TimePickerDialog(activity,
                TimePickerDialog.OnTimeSetListener(function = { view, h, m ->

                Toast.makeText(activity, h.toString() + " : " + m +" : " , Toast.LENGTH_LONG).show()
                    btn.setText(h.toString() + " : " + m.toString())

            }),hour,minute,true)

            tpd.show() }

        // Inflate the layout for this fragment
        return view

    }



}