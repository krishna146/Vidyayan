package com.bcebhagalpur.welcomeslider.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.adapter.MyCourseAdaptor
import com.bcebhagalpur.welcomeslider.model.MyCourse

class MyCoursesActivity : AppCompatActivity() {
    lateinit var recyclerView_two: RecyclerView
    private lateinit var myCourseAdaptor: MyCourseAdaptor
    private val course_list= arrayListOf<MyCourse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_courses)
        recyclerView_two=findViewById(R.id.mycourse_recyclerView)
        val course_object1=MyCourse(R.drawable.tp,"DSA","Ashutosh kumar")
        val course_object2=MyCourse(R.drawable.tp,"DSA","Ashutosh kumar")
        val course_object3=MyCourse(R.drawable.tp,"DSA","Ashutosh kumar")
        val course_object4=MyCourse(R.drawable.tp,"DSA","Ashutosh kumar")
        val course_object5=MyCourse(R.drawable.tp,"DSA","Ashutosh kumar")
        val course_object6=MyCourse(R.drawable.tp,"DSA","Ashutosh kumar")
        course_list.add(course_object1)
        course_list.add(course_object2)
        course_list.add(course_object3)
        course_list.add(course_object4)
        course_list.add(course_object5)
        course_list.add(course_object6)
        myCourseAdaptor= MyCourseAdaptor(this,course_list)
        recyclerView_two.adapter=myCourseAdaptor
        recyclerView_two.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }
}