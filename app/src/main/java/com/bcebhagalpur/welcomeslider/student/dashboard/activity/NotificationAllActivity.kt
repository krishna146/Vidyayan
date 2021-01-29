package com.bcebhagalpur.welcomeslider.student.dashboard.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.adapter.NotificationAdapter
import com.bcebhagalpur.welcomeslider.student.dashboard.model.NotifiationModel

class NotificationAllActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private val notification_list= arrayListOf<NotifiationModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_all)
        recyclerView=findViewById(R.id.notifyAllRecyclerView)
        toolbar=findViewById(R.id.notifyAllToolbar)
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
        notificationAdapter= NotificationAdapter(this,notification_list,notification_list.size)
        recyclerView.adapter=notificationAdapter
        recyclerView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
       mainToolBar()
    }
    fun mainToolBar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Notification"
    }
}