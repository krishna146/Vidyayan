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
import com.bcebhagalpur.welcomeslider.model.Syllabus
import com.bcebhagalpur.welcomeslider.model.Teacherlist

class SyllabusAdapter(private val context: Context, private val itemList: ArrayList<Syllabus>) : RecyclerView.Adapter<SyllabusAdapter.SyllabusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SyllabusViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.subjects, parent, false)
        return SyllabusViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: SyllabusViewHolder, position: Int) {

        val subject = itemList[position]
        holder.sub_name.text = subject.sub_name
        holder.syllabus_coverPIc.setImageResource(subject.syllabus_coverPIc)


    }

    class SyllabusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sub_name: TextView = view.findViewById(R.id.subject)
        val syllabus_coverPIc: ImageView = view.findViewById(R.id.sub_image)

    }
}

