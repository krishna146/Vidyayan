package com.bcebhagalpur.welcomeslider.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.model.TeacherListModel
import com.bcebhagalpur.welcomeslider.model.Teacherlist

class TEacherAdapter(private val context: Context, private val itemList: ArrayList<Teacherlist>) : RecyclerView.Adapter<TEacherAdapter.TeacherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.teacher, parent, false)
        return TeacherViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {

        val teacher = itemList[position]
        holder.txtTeacherName.text = teacher.teachername
        holder.teacherImage.setImageResource(teacher.profilepic)
        holder.txtTeacherSubject.text=teacher.txtTeacherSubject
        holder.txtTeacherstudied.text=teacher.txtTeacherstudied
        holder.one.text=teacher.one
        holder.two.text=teacher.two
        holder.three.text=teacher.three
        holder.four.text=teacher.four

    }

    class TeacherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTeacherName: TextView = view.findViewById(R.id.name_teacher)
        val teacherImage: ImageView = view.findViewById(R.id.teacher_pics)
        val txtTeacherSubject: TextView = view.findViewById(R.id.sub_expertise)
        val txtTeacherstudied: TextView = view.findViewById(R.id.college_name)
        val linearLayout:LinearLayout = view.findViewById(R.id.llt)
        val one:TextView=view.findViewById(R.id.one)
        val two:TextView=view.findViewById(R.id.two)
        val three:TextView=view.findViewById(R.id.three)
        val four:TextView=view.findViewById(R.id.four)
    }
}

