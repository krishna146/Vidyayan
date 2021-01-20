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
import com.bcebhagalpur.welcomeslider.model.StudentAround
import com.bcebhagalpur.welcomeslider.model.Teacherlist

class StudentAroundAdapter(private val context: Context, private val itemList: ArrayList<StudentAround>) : RecyclerView.Adapter<StudentAroundAdapter.StudentAroundViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAroundViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.studentaround, parent, false)
        return StudentAroundViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: StudentAroundViewHolder, position: Int) {

        val student = itemList[position]
        holder.name_student.text = student.name_student
        holder.student_pics.setImageResource(student.student_pics)
        holder.student_class.text=student.student_class
        holder.student_address.text=student.student_address
        holder.one.text=student.one
        holder.two.text=student.two
        holder.three.text=student.three
        holder.four.text=student.four

    }

    class StudentAroundViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val student_pics:ImageView=view.findViewById(R.id.student_pics)
        val name_student:TextView=view.findViewById(R.id.name_student)
        val student_class:TextView=view.findViewById(R.id.student_class)
        val student_address:TextView=view.findViewById(R.id.student_address)
        val linearLayout: LinearLayout = view.findViewById(R.id.llt)
        val one: TextView =view.findViewById(R.id.one)
        val two: TextView =view.findViewById(R.id.two)
        val three: TextView =view.findViewById(R.id.three)
        val four: TextView =view.findViewById(R.id.four)
    }
}

