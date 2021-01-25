package com.bcebhagalpur.welcomeslider.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.adapter.ChatExploreAdapter
import com.bcebhagalpur.welcomeslider.adapter.CourseAdapter
import com.bcebhagalpur.welcomeslider.adapter.TeacherListAdapter
import com.bcebhagalpur.welcomeslider.model.ChatExplore
import com.bcebhagalpur.welcomeslider.model.CourseListModel

class ChatExploreActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: ChatExploreAdapter
    private val chatInfoList = arrayListOf<ChatExplore>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_explore)
        toolbar=findViewById(R.id.messageToolbar)
        recyclerView=findViewById(R.id.recycler_explore)
        val chatExplore_object1=ChatExplore(R.drawable.tp,"Ak")
        val chatExplore_object2=ChatExplore(R.drawable.tp,"Ak")
        val chatExplore_object3=ChatExplore(R.drawable.tp,"Ak")
        val chatExplore_object4=ChatExplore(R.drawable.tp,"Ak")
        chatInfoList.add(chatExplore_object1)
        chatInfoList.add(chatExplore_object2)
        chatInfoList.add(chatExplore_object3)
        chatInfoList.add(chatExplore_object4)
        recyclerAdapter = ChatExploreAdapter(this, chatInfoList)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        mainToolBar()
    }
    fun mainToolBar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Chat Explore"
    }
}