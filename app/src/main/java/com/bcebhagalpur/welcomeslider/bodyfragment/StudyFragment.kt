package com.bcebhagalpur.welcomeslider.bodyfragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.adapter.MyCourseAdaptor
import com.bcebhagalpur.welcomeslider.adapter.ScheduleAdapter
import com.bcebhagalpur.welcomeslider.adapter.StudentSubjectAdapter
import com.bcebhagalpur.welcomeslider.adapter.SyllabusAdapter
import com.bcebhagalpur.welcomeslider.model.MyCourse
import com.bcebhagalpur.welcomeslider.model.Schedule
import com.bcebhagalpur.welcomeslider.model.StudentSubject
import com.bcebhagalpur.welcomeslider.model.Syllabus


class StudyFragment : Fragment() {
/*
    lateinit var recyclerView_one: RecyclerView
*/
    private lateinit var studentSubjectAdapter:StudentSubjectAdapter
    private val subject_list= arrayListOf<StudentSubject>()

    lateinit var recyclerView_two: RecyclerView
    private lateinit var myCourseAdaptor: MyCourseAdaptor
    private val course_list= arrayListOf<MyCourse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_study, container, false)
/*
        recyclerView_one=view.findViewById(R.id.study_recyclerView_one)
*/

       /* val subject_object1= StudentSubject(R.drawable.tp,"Mathematics")
        val subject_object2= StudentSubject(R.drawable.tp,"Mathematics")
        val subject_object3= StudentSubject(R.drawable.tp,"Mathematics")
        val subject_object4= StudentSubject(R.drawable.tp,"Mathematics")
        val subject_object5= StudentSubject(R.drawable.tp,"Mathematics")
        val subject_object6= StudentSubject(R.drawable.tp,"Mathematics")
        subject_list.add(subject_object1)
        subject_list.add(subject_object2)
        subject_list.add(subject_object3)
        subject_list.add(subject_object4)
        subject_list.add(subject_object5)
        subject_list.add(subject_object6)
        studentSubjectAdapter= StudentSubjectAdapter(activity as Context,subject_list)
        recyclerView_one.adapter=studentSubjectAdapter
        recyclerView_one.layoutManager= LinearLayoutManager(activity as Context, LinearLayoutManager.HORIZONTAL,false)
*/
        recyclerView_two=view.findViewById(R.id.study_recyclerView_two)
        val course_object1=MyCourse(R.drawable.teacher_logo,"DSA","Ashutosh kumar")
        val course_object2=MyCourse(R.drawable.teacher_logo,"DSA","Ashutosh kumar")
        val course_object3=MyCourse(R.drawable.teacher_logo,"DSA","Ashutosh kumar")
        val course_object4=MyCourse(R.drawable.teacher_logo,"DSA","Ashutosh kumar")
        val course_object5=MyCourse(R.drawable.teacher_logo,"DSA","Ashutosh kumar")
        val course_object6=MyCourse(R.drawable.teacher_logo,"DSA","Ashutosh kumar")
        course_list.add(course_object1)
        course_list.add(course_object2)
        course_list.add(course_object3)
        course_list.add(course_object4)
        course_list.add(course_object5)
        course_list.add(course_object6)
        myCourseAdaptor= MyCourseAdaptor(activity as Context,course_list)
        recyclerView_two.adapter=myCourseAdaptor
        recyclerView_two.layoutManager=LinearLayoutManager(activity as Context,LinearLayoutManager.HORIZONTAL,false)

        return view
    }


}