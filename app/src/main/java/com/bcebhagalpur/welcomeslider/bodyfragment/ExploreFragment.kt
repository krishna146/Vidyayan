package com.bcebhagalpur.welcomeslider.bodyfragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.activity.LoginActivity
import com.bcebhagalpur.welcomeslider.adapter.CourseAdapter
import com.bcebhagalpur.welcomeslider.adapter.PagerAdapterTwo
import com.bcebhagalpur.welcomeslider.adapter.TeacherListAdapter
import com.bcebhagalpur.welcomeslider.model.CourseListModel
import com.bcebhagalpur.welcomeslider.model.TeacherListModel

class ExploreFragment : Fragment() {

    private lateinit var img1: ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var img4:ImageView
    private lateinit var img5:ImageView
    private lateinit var img6:ImageView

    private lateinit var viewPager:ViewPager
    lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: TeacherListAdapter
    private val teacherInfoInfoList = arrayListOf<TeacherListModel>()
    lateinit var recyclerViewCourse: RecyclerView
    private lateinit var recyclerAdapterCourse: CourseAdapter
    private val courseInfoInfoList = arrayListOf<CourseListModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_explore, container, false)

        img1=view.findViewById(R.id.indicator1)
        img2=view.findViewById(R.id.indicator2)
        img3=view.findViewById(R.id.indicator3)
        img4=view.findViewById(R.id.indicator4)
        img5=view.findViewById(R.id.indicator5)
        img6=view.findViewById(R.id.indicator6)

        viewPager=view.findViewById(R.id.viewPager)

        recyclerView=view.findViewById(R.id.recyclerTeacherList)

        recyclerViewCourse=view.findViewById(R.id.recyclerCourses)

        val collegeObject=TeacherListModel(R.drawable.slider1,"Aashutosh rana","Android development kotlin",
            "i have more than 3 year experience in android development and 15 year experience in programming")
        val collegeObject1=TeacherListModel(R.drawable.slider1,"Aashutosh rana","Android development kotlin",
            "i have more than 3 year experience in android development and 15 year experience in programming")
        val collegeObject2=TeacherListModel(R.drawable.slider1,"Aashutosh rana","Android development kotlin",
            "i have more than 3 year experience in android development and 15 year experience in programming")
        val collegeObject3=TeacherListModel(R.drawable.slider1,"Aashutosh rana","Android development kotlin",
            "i have more than 3 year experience in android development and 5 year experience in other programming")
        teacherInfoInfoList.add(collegeObject)
        teacherInfoInfoList.add(collegeObject1)
        teacherInfoInfoList.add(collegeObject2)
        teacherInfoInfoList.add(collegeObject3)

        val courseObject=CourseListModel(R.drawable.slider,"Course name","Course price")
        val courseObject1=CourseListModel(R.drawable.slider1,"Physics","800")
        val courseObject2=CourseListModel(R.drawable.slider1,"Mathematics","900")
        val courseObject3=CourseListModel(R.drawable.slider,"English","2000")
        courseInfoInfoList.add(courseObject)
        courseInfoInfoList.add(courseObject1)
        courseInfoInfoList.add(courseObject2)
        courseInfoInfoList.add(courseObject3)



        initRecyclerTeacher()
        initRecyclerCourse()
        imageSwitcher()
        return view
    }

    private fun initRecyclerTeacher() {
        recyclerAdapter = TeacherListAdapter(activity as Context, teacherInfoInfoList)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager=LinearLayoutManager(activity as Context, LinearLayoutManager.HORIZONTAL,false)

    }

    private fun initRecyclerCourse() {
        recyclerAdapterCourse = CourseAdapter(activity as Context, courseInfoInfoList)
        recyclerViewCourse.adapter = recyclerAdapterCourse
        recyclerViewCourse.layoutManager=LinearLayoutManager(activity as Context, LinearLayoutManager.HORIZONTAL,false)

    }

    private fun imageSwitcher(){
        val imageAdapter=PagerAdapterTwo(activity as Context)
        viewPager.adapter=imageAdapter

        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)
            {

            }

            override fun onPageSelected(position: Int) {

                when(viewPager.currentItem){
                    0->{
                        img1.setImageResource(R.drawable.indicator_circle_black)
                        img2.setImageResource(R.drawable.indicator_circle)
                        img3.setImageResource(R.drawable.indicator_circle)
                        img4.setImageResource(R.drawable.indicator_circle)
                        img5.setImageResource(R.drawable.indicator_circle)
                        img6.setImageResource(R.drawable.indicator_circle)
                    }
                    1->{
                        img1.setImageResource(R.drawable.indicator_circle)
                        img2.setImageResource(R.drawable.indicator_circle_black)
                        img3.setImageResource(R.drawable.indicator_circle)
                        img4.setImageResource(R.drawable.indicator_circle)
                        img5.setImageResource(R.drawable.indicator_circle)
                        img6.setImageResource(R.drawable.indicator_circle)
                    }
                    2->{
                        img1.setImageResource(R.drawable.indicator_circle)
                        img2.setImageResource(R.drawable.indicator_circle)
                        img3.setImageResource(R.drawable.indicator_circle_black)
                        img4.setImageResource(R.drawable.indicator_circle)
                        img5.setImageResource(R.drawable.indicator_circle)
                        img6.setImageResource(R.drawable.indicator_circle)
                    }
                    3->{
                        img1.setImageResource(R.drawable.indicator_circle)
                        img2.setImageResource(R.drawable.indicator_circle)
                        img3.setImageResource(R.drawable.indicator_circle)
                        img4.setImageResource(R.drawable.indicator_circle_black)
                        img5.setImageResource(R.drawable.indicator_circle)
                        img6.setImageResource(R.drawable.indicator_circle)
                    }
                    4->{
                        img1.setImageResource(R.drawable.indicator_circle)
                        img2.setImageResource(R.drawable.indicator_circle)
                        img3.setImageResource(R.drawable.indicator_circle)
                        img4.setImageResource(R.drawable.indicator_circle)
                        img5.setImageResource(R.drawable.indicator_circle_black)
                        img6.setImageResource(R.drawable.indicator_circle)
                    }
                    5->{
                        img1.setImageResource(R.drawable.indicator_circle)
                        img2.setImageResource(R.drawable.indicator_circle)
                        img3.setImageResource(R.drawable.indicator_circle)
                        img4.setImageResource(R.drawable.indicator_circle)
                        img5.setImageResource(R.drawable.indicator_circle)
                        img6.setImageResource(R.drawable.indicator_circle_black)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

}