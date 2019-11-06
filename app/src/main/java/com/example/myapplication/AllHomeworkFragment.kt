package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_all_homework.*

class AllHomeworkFragment : Fragment(){

    companion object {
        fun newInstance() = AllHomeworkFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_all_homework, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        val homeworkAdapter = activity?.applicationContext?.let { HomeworkAdapter(list, it) }

        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, //แนว
                false)
            isNestedScrollingEnabled = false
            adapter = homeworkAdapter
            onFlingListener = null
        }

        homeworkAdapter?.notifyDataSetChanged()
    }

}