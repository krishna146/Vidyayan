package com.bcebhagalpur.welcomeslider.student.navigationDrawer.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.adapter.CourseAdapter
import com.bcebhagalpur.welcomeslider.model.CourseListModel

class BookmarkActivity : AppCompatActivity() {
    lateinit var recyclerViewCourse: RecyclerView
    private lateinit var recyclerAdapterCourse: CourseAdapter
    private val courseInfoInfoList = arrayListOf<CourseListModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)
        recyclerViewCourse=findViewById(R.id.bookmark_recyclerview)
        val courseObject=CourseListModel(R.drawable.tp,"Course name","Course price")
        val courseObject1=CourseListModel(R.drawable.tp,"Physics","800")
        val courseObject2=CourseListModel(R.drawable.tp,"Mathematics","900")
        val courseObject3=CourseListModel(R.drawable.tp,"English","2000")
        courseInfoInfoList.add(courseObject)
        courseInfoInfoList.add(courseObject1)
        courseInfoInfoList.add(courseObject2)
        courseInfoInfoList.add(courseObject3)
        recyclerAdapterCourse = CourseAdapter(this, courseInfoInfoList)
        recyclerViewCourse.adapter = recyclerAdapterCourse
        recyclerViewCourse.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }
}