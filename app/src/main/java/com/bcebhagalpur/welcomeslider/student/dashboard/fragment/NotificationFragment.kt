package com.bcebhagalpur.welcomeslider.student.dashboard.fragment

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.activity.LoginActivity
import com.bcebhagalpur.welcomeslider.adapter.MyCourseAdaptor
import com.bcebhagalpur.welcomeslider.model.MyCourse
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.NotificationAllActivity
import com.bcebhagalpur.welcomeslider.student.dashboard.adapter.NotificationAdapter
import com.bcebhagalpur.welcomeslider.student.dashboard.model.NotifiationModel
import kotlinx.android.synthetic.main.fragment_notification.*


class NotificationFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private val notification_list= arrayListOf<NotifiationModel>()
    lateinit var notify_btn:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view=inflater.inflate(R.layout.fragment_notification, container, false)
        recyclerView=view.findViewById(R.id.recycler_notification)
        notify_btn=view.findViewById(R.id.notify_but)
        //toolbar=view.findViewById(R.id.notificationToolbar)
        val notification_object1=NotifiationModel(R.drawable.tp,"Ashutosh","DSA")
        val notification_object2=NotifiationModel(R.drawable.tp,"Ashutosh","DSA")
        val notification_object3=NotifiationModel(R.drawable.tp,"Ashutosh","DSA")
        val notification_object4=NotifiationModel(R.drawable.tp,"Ashutosh","DSA")
        val notification_object5=NotifiationModel(R.drawable.tp,"Ashutosh","DSA")
        notification_list.add(notification_object1)
        notification_list.add(notification_object2)
        notification_list.add(notification_object3)
        notification_list.add(notification_object4)
        notification_list.add(notification_object5)
        notificationAdapter= NotificationAdapter(activity as Context,notification_list,3)
        recyclerView.adapter=notificationAdapter
        recyclerView.layoutManager=
            LinearLayoutManager(activity as Context, LinearLayoutManager.VERTICAL,false)

         notify_btn.setOnClickListener {
             startActivity(Intent(activity as Context ,NotificationAllActivity::class.java))
         }
        return view
    }


}