package com.example.myapplication.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.recycle_schedule.view.*

class ScheduleAdapter (private val items : ArrayList<Subject>): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycle_schedule, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        fun bind(subject: Subject) {
            itemView.apply {
                subject_name.text = subject.name
                var startTimeAndEndTime = subject.startTime + " - " + subject.endTime
                start_and_end_time.text = startTimeAndEndTime
                classroom.text = subject.classroom
            }
        }
    }
}