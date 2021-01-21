package com.bcebhagalpur.welcomeslider.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.model.StudentSubject
import com.bcebhagalpur.welcomeslider.model.Syllabus

class StudentSubjectAdapter(private val context: Context, private val itemList: ArrayList<StudentSubject>) : RecyclerView.Adapter<StudentSubjectAdapter.StudentSubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentSubjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.subject_student, parent, false)
        return StudentSubjectViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: StudentSubjectViewHolder, position: Int) {

        val subject = itemList[position]
        holder.sub_name_stu.text = subject.sub_name_stu
        holder.subject_pic.setImageResource(subject.sub_pic)


    }

    class StudentSubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sub_name_stu: TextView = view.findViewById(R.id.student_subject)
        val subject_pic: ImageView = view.findViewById(R.id.subject_pic)

    }
}

