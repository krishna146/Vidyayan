package com.bcebhagalpur.welcomeslider.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.model.MyCourse

class MyCourseAdaptor(private val context: Context, private val itemList: ArrayList<MyCourse>) : RecyclerView.Adapter<MyCourseAdaptor.MyCourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCourseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.my_courses, parent, false)
        return MyCourseViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyCourseViewHolder, position: Int) {

        val course = itemList[position]
        holder.course_name.text = course.course_name
        holder.course_teacher_name.text=course.course_teacher
        holder.course_pic.setImageResource(course.course_pic)


    }

    class MyCourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val course_name: TextView = view.findViewById(R.id.course_name)
        val course_teacher_name: TextView = view.findViewById(R.id.course_teacher)
        val course_pic: ImageView = view.findViewById(R.id.course_image)

    }
}

