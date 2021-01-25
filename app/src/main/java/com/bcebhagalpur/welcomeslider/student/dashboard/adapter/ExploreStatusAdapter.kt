package com.bcebhagalpur.welcomeslider.student.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.model.ExploreStatusModel

class ExploreStatusAdapter(private val context: Context, private val itemList: ArrayList<ExploreStatusModel>) : RecyclerView.Adapter<ExploreStatusAdapter.StatusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.student_explore_top_status, parent, false)
        return StatusViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {

        val subject = itemList[position]
        holder.subject_pic.setImageResource(subject.sub_pic)
    }

    class StatusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subject_pic: ImageView = view.findViewById(R.id.subject_pic)
    }
}
