package com.example.myapplication.model

import android.app.AlertDialog
import android.graphics.Color
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DatabaseHandler
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


    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView), View.OnLongClickListener {
        private var dbHandler: DatabaseHandler? = null

        init {
            itemView.setOnLongClickListener(this)
        }

        fun bind(subject: Subject) {
            itemView.apply {
                subject_name.text = subject.name
                var startTimeAndEndTime = subject.startTime + " - " + subject.endTime
                start_and_end_time.text = startTimeAndEndTime
                classroom.text = subject.classroom
                subject_id.text = subject.id.toString()
            }
        }

        override fun onLongClick(view: View?): Boolean {
            dbHandler = DatabaseHandler(view!!.context)
            var subjectId : String
            itemView.apply {
                subjectId = subject_id.text as String
            }

            val builder = AlertDialog.Builder(view!!.context)
            builder.setTitle("Delete")
            builder.setMessage("Do you want to delete this subject?")

            builder.setPositiveButton("Delete"){dialog, which ->
                dbHandler?.deleteSubject(subjectId)
                Toast.makeText(view!!.context, "Delete success",Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("Cancel"){dialog,which ->
                Toast.makeText(view!!.context,subjectId,Toast.LENGTH_SHORT).show()
            }


            val dialog: AlertDialog = builder.create()

            dialog.show()

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(Color.RED)

            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.WHITE)


            return true
        }

    }
}