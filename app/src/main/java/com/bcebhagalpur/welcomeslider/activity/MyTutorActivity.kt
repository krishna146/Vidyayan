package com.bcebhagalpur.welcomeslider.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.adapter.TEacherAdapter
import com.bcebhagalpur.welcomeslider.model.Teacherlist

class MyTutorActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: TEacherAdapter
    private val teacherInfoInfoList = arrayListOf<Teacherlist>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tutor)
        recyclerView=findViewById(R.id.teachers_list)
        //  val teacherobject=Teacherlist("Suresh Vidyarthi",R.drawable.tp,"Computer Wizard","BCE","Taught and mentored more than 1000 students","Taught and mentored more than 1000 students)

        val teacherobject1=Teacherlist("Suresh Vidyarthi",R.drawable.tp,"computer Wizard","BCE","Taught and mentored more than 1000 students","30+ years teaching experience","Secured rank 1 in upsc","90+ iit selection")
        val teacherobject2=Teacherlist("Suresh Vidyarthi",R.drawable.tp,"computer Wizard","BCE","Taught and mentored more than 1000 students","30+ years teaching experience","Secured rank 1 in upsc","90+ iit selection")
        val teacherobject3=Teacherlist("Suresh Vidyarthi",R.drawable.tp,"computer Wizard","BCE","Taught and mentored more than 1000 students","30+ years teaching experience","Secured rank 1 in upsc","90+ iit selection")
        val teacherobject4=Teacherlist("Suresh Vidyarthi",R.drawable.tp,"computer Wizard","BCE","Taught and mentored more than 1000 students","30+ years teaching experience","Secured rank 1 in upsc","90+ iit selection")
        val teacherobject5=Teacherlist("Suresh Vidyarthi",R.drawable.tp,"computer Wizard","BCE","Taught and mentored more than 1000 students","30+ years teaching experience","Secured rank 1 in upsc","90+ iit selection")
        teacherInfoInfoList.add(teacherobject1)
        teacherInfoInfoList.add(teacherobject2)
        teacherInfoInfoList.add(teacherobject3)
        teacherInfoInfoList.add(teacherobject4)
        teacherInfoInfoList.add(teacherobject5)
        recyclerAdapter = TEacherAdapter(this, teacherInfoInfoList)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }
}