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
import com.bcebhagalpur.welcomeslider.model.TeacherListModel

class TeacherListAdapter(private val context: Context, private val itemList: ArrayList<TeacherListModel>) : RecyclerView.Adapter<TeacherListAdapter.TeacherViewHplder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHplder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.teacher_list_item, parent, false)
        return TeacherViewHplder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: TeacherViewHplder, position: Int) {

        val teacher = itemList[position]
        holder.txtTeacherName.text = teacher.teacherName
        holder.txtTeacherSubject.text = teacher.teacherSubject
        holder.txtTeacherExperience.text = teacher.teacherExperience
        holder.teacherImage.setImageResource(teacher.teacherImage)
       holder.cardView.animation=AnimationUtils.loadAnimation(context,R.anim.horizontal_teacher_animation)
    }

    class TeacherViewHplder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTeacherName: TextView = view.findViewById(R.id.txtTeacherName)
        val teacherImage: ImageView = view.findViewById(R.id.imgProfile)
        val txtTeacherSubject: TextView = view.findViewById(R.id.txtTeacherSubject)
        val txtTeacherExperience: TextView = view.findViewById(R.id.txtTeacherExperience)
        val cardView: CardView = view.findViewById(R.id.cardView)
    }
}

