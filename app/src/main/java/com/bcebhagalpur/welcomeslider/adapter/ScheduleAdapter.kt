package com.bcebhagalpur.welcomeslider.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.model.Schedule
import com.bcebhagalpur.welcomeslider.model.Teacherlist

class ScheduleAdapter(private val context: Context, private val itemList: ArrayList<Schedule>) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.schedule_teacher, parent, false)
        return ScheduleViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {

        val schedule = itemList[position]
        holder.name_Student.text=schedule.name_Student
        holder.mode_of_education.text=schedule.mode_of_education
        holder.time_duration.text=schedule.time_duration
        holder.student_Address.text=schedule.student_Address
        holder.student_teacherExplore_pics.setImageResource(schedule.student_teacherExplore_pics)

    }

    class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val student_teacherExplore_pics:ImageView=view.findViewById(R.id.student_teacherExplore_pics)
        val name_Student:TextView=view.findViewById(R.id.name_Student)
        val time_duration:TextView=view.findViewById(R.id.time_duration)
        val mode_of_education:TextView=view.findViewById(R.id.mode_of_education)
        val student_Address:TextView=view.findViewById(R.id.student_Address)
    }
}

