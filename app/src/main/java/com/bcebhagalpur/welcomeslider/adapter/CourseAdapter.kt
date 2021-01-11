package com.bcebhagalpur.welcomeslider.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.model.CourseListModel
import com.bcebhagalpur.welcomeslider.model.TeacherListModel

class CourseAdapter(private val context: Context, private val itemList: ArrayList<CourseListModel>) : RecyclerView.Adapter<CourseAdapter.TeacherViewHplder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHplder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.courses_item, parent, false)
        return TeacherViewHplder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: TeacherViewHplder, position: Int) {

        val course = itemList[position]
        holder.txtCourseName.text = course.courseName
        holder.txtPrice.text = course.coursePrice
        holder.imgCourse.setImageResource(course.courseImage)
       holder.cardView.animation=AnimationUtils.loadAnimation(context,R.anim.horizontal_teacher_animation)
    }

    class TeacherViewHplder(view: View) : RecyclerView.ViewHolder(view) {
        val txtCourseName: TextView = view.findViewById(R.id.txtSubject)
        val imgCourse: ImageView = view.findViewById(R.id.imgCourse)
        val txtPrice: TextView = view.findViewById(R.id.txtPrice)
        val cardView: CardView = view.findViewById(R.id.cardView)
    }
}

