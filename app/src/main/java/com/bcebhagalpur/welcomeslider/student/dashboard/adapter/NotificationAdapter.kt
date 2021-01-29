package com.bcebhagalpur.welcomeslider.student.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.adapter.MyCourseAdaptor
import com.bcebhagalpur.welcomeslider.model.MyCourse
import com.bcebhagalpur.welcomeslider.student.dashboard.model.NotifiationModel

class NotificationAdapter (private val context: Context, private val itemList: ArrayList<NotifiationModel>,private val sizee:Int) : RecyclerView.Adapter<NotificationAdapter.MyNotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyNotificationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.notification, parent, false)
        return MyNotificationViewHolder(view)

    }

    override fun getItemCount(): Int {
        if (itemList.size<3) {
            return itemList.size
        }else{
            return sizee
        }


    }


    override fun onBindViewHolder(holder: MyNotificationViewHolder, position: Int) {

        val notification = itemList[position]
        holder.notification_name.text = notification.notification_name
        holder.notification_subject.text=notification.notification_subjet
        holder.notification_image.setImageResource(notification.notification_image)


    }

    class MyNotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val notification_name: TextView = view.findViewById(R.id.notification_name)
        val notification_subject: TextView = view.findViewById(R.id.notification_subject)
        val notification_image: ImageView = view.findViewById(R.id.image_notification)

    }
}

