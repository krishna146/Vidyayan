package com.bcebhagalpur.welcomeslider.student.dashboard.fragment

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.Swipe
import com.bcebhagalpur.welcomeslider.activity.BlankActivity2
import com.bcebhagalpur.welcomeslider.student.dashboard.adapter.ExploreStatusAdapter
import com.bcebhagalpur.welcomeslider.student.dashboard.adapter.ExploreTeacherListAdapter
import com.bcebhagalpur.welcomeslider.student.dashboard.model.ExploreStatusModel
import com.bcebhagalpur.welcomeslider.student.dashboard.model.ExploreTeacherListModel
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.fragment_explore.*
import java.lang.NullPointerException
import java.util.*


class ExploreFragment : Fragment() {
    //private lateinit var txt:TextView
//    private lateinit var searchView: androidx.appcompat.widget.SearchView
////    private lateinit var recyclerStatus:RecyclerView
//    private lateinit var c1:CardView
    private lateinit var recyclerTeacher: RecyclerView
    private lateinit var exploreStatusAdapter: ExploreStatusAdapter
    private val exploreStatusModel = arrayListOf<ExploreStatusModel>()

    //teacher
    private lateinit var exploreTeacherListAdapter: ExploreTeacherListAdapter
    private val exploreTeacherListModel = arrayListOf<ExploreTeacherListModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_explore, container, false)

//        changeColor(R.color.white)

//        setUpToolbar()

        val statusObject = ExploreStatusModel(R.drawable.tutoring)
        val statusObject1 = ExploreStatusModel(R.drawable.tp)
        val statusObject2 = ExploreStatusModel(R.drawable.slider)
        val statusObject3 = ExploreStatusModel(R.drawable.seven)
        val statusObject4 = ExploreStatusModel(R.drawable.twelve)
        val statusObject5 = ExploreStatusModel(R.drawable.eleven)

        exploreStatusModel.add(statusObject)
        exploreStatusModel.add(statusObject1)
        exploreStatusModel.add(statusObject2)
        exploreStatusModel.add(statusObject3)
        exploreStatusModel.add(statusObject4)
        exploreStatusModel.add(statusObject5)

//        searchView=view.findViewById(R.id.searchView)
//        c1=view.findViewById(R.id.c2)
//        recyclerStatus=view.findViewById(R.id.recyclerStatus)
//        txt=view.findViewById(R.id.txt)
        recyclerTeacher = view.findViewById(R.id.recyclerTeacher)

//        txt.setOnClickListener {
////            c1.visibility=View.VISIBLE
////            txt.visibility=View.GONE
//        }

        //teacher
        val schedule_object1 = ExploreTeacherListModel(
            R.drawable.tp, "Ashutosh Kumar",
            "10A.M-12A.M", "Online",
            "BCE,Sabour,Bhagalpur", "Mathematics, English", "10:00 AM-12:00AM",
            "2000-4000 in ruppess", "", 3f
        )
        val schedule_object2 = ExploreTeacherListModel(
            R.drawable.tp, "Ashutosh Kumar",
            "10A.M-12A.M", "Online",
            "BCE,Sabour,Bhagalpur", "Mathematics, English", "10:00 AM-12:00AM",
            "2000-4000 in ruppess", "", 4f
        )
        exploreTeacherListModel.add(schedule_object1)
        exploreTeacherListModel.add(schedule_object1)
        exploreTeacherListModel.add(schedule_object2)
        exploreTeacherListModel.add(schedule_object2)
        exploreTeacherListModel.add(schedule_object1)
        exploreTeacherListModel.add(schedule_object1)
        exploreTeacherListModel.add(schedule_object2)
        exploreTeacherListModel.add(schedule_object2)
        exploreTeacherListModel.add(schedule_object1)
        exploreTeacherListModel.add(schedule_object1)
        exploreTeacherListModel.add(schedule_object2)
        exploreTeacherListModel.add(schedule_object2)

        /*initRecyclerStatus()*/
        initRecyclerTeacher()

        return view

    }

    //    private fun initRecyclerStatus() {
//        exploreStatusAdapter=ExploreStatusAdapter(activity as Context, exploreStatusModel)
//        recyclerStatus.adapter = exploreStatusAdapter
//        recyclerStatus.layoutManager=LinearLayoutManager(
//            activity as Context,
//            LinearLayoutManager.HORIZONTAL,
//            false
//        )
//    }
    private fun initRecyclerTeacher() {
        val layoutManager = LinearLayoutManager(activity as Context)
        exploreTeacherListAdapter = ExploreTeacherListAdapter(
            activity as Context,
            exploreTeacherListModel
        )
        recyclerTeacher.adapter = exploreTeacherListAdapter
        recyclerTeacher.layoutManager = layoutManager
//        val dividerItemDecoration =
//            DividerItemDecoration(recyclerTeacher.context, layoutManager.orientation)
//        recyclerTeacher.addItemDecoration(dividerItemDecoration)

        val item = object : Swipe(activity as Context, 0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                //  var fromPosition=viewHolder.adapterPosition
                //  var toPosition=viewHolder.adapterPosition
                //  Collections.swap(teacherInfoInfoList,fromPosition,toPosition)
                //  recyclerView.adapter!!.notifyItemMoved(toPosition,fromPosition)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Toast.makeText(activity as Context, "Senorita", Toast.LENGTH_SHORT).show()
                startActivity(Intent(activity as Context, BlankActivity2::class.java))
                exploreTeacherListAdapter.notifyItemChanged(viewHolder.adapterPosition)
                val fromPosition = viewHolder.adapterPosition
                val toPosition = viewHolder.adapterPosition
                Collections.swap(exploreTeacherListModel, fromPosition, toPosition)
                recyclerTeacher.adapter!!.notifyItemMoved(toPosition, fromPosition)

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {


            }
        }
        val itemTouchHelper = ItemTouchHelper(item)
        itemTouchHelper.attachToRecyclerView(recyclerTeacher)
    }

//    fun setUpToolbar() {
//        activity!!.setActionBar(toolBar)
//        try {
//            activity!!.actionBar!!.title = "Explore Teachers"
//            activity!!.actionBar?.setHomeButtonEnabled(true)
//            activity!!.actionBar?.setDisplayHomeAsUpEnabled(true)
//        }catch (e:NullPointerException){
//            e.printStackTrace()
//        }


//    fun changeColor(resourseColor: Int) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//           activity?.window!!.statusBarColor = ContextCompat.getColor(
//               activity as Context,
//               resourseColor
//           )
//            try {
//                val bar: ActionBar = activity!!.actionBar!!
//                bar.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.white)))
//                activity!!.actionBar!!.title="Teachers according to your preferences"
//            }catch (e:NullPointerException){
//                e.printStackTrace()
//            }
//
//        }
//    }
}

//
//        private lateinit var img1: ImageView
//    private lateinit var img2: ImageView
//    private lateinit var img3: ImageView
//    private lateinit var img4:ImageView
//    private lateinit var img5:ImageView
//    private lateinit var img6:ImageView
//
//    private lateinit var viewPager:ViewPager
//    lateinit var recyclerView: RecyclerView
//    private lateinit var recyclerAdapter: TeacherListAdapter
//    private val teacherInfoInfoList = arrayListOf<TeacherListModel>()
//    lateinit var recyclerViewCourse: RecyclerView
//    private lateinit var recyclerAdapterCourse: CourseAdapter
//    private val courseInfoInfoList = arrayListOf<CourseListModel>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val view= inflater.inflate(R.layout.fragment_explore, container, false)
//
//        img1=view.findViewById(R.id.indicator1)
//        img2=view.findViewById(R.id.indicator2)
//        img3=view.findViewById(R.id.indicator3)
//        img4=view.findViewById(R.id.indicator4)
//        img5=view.findViewById(R.id.indicator5)
//        img6=view.findViewById(R.id.indicator6)
//
//        viewPager=view.findViewById(R.id.viewPager)
//
//        recyclerView=view.findViewById(R.id.recyclerTeacherList)
//
//        recyclerViewCourse=view.findViewById(R.id.recyclerCourses)
//
//
//        val collegeObject1=TeacherListModel(R.drawable.slider1,"Aashutosh rana","Android development kotlin",
//            "i have more than 3 year experience in android development and 15 year experience in programming")
//        val collegeObject2=TeacherListModel(R.drawable.slider1,"Aashutosh rana","Android development kotlin",
//            "i have more than 3 year experience in android development and 15 year experience in programming")
//        val collegeObject3=TeacherListModel(R.drawable.slider1,"Aashutosh rana","Android development kotlin",
//            "i have more than 3 year experience in android development and 5 year experience in other programming")
//
//        teacherInfoInfoList.add(collegeObject)
//        teacherInfoInfoList.add(collegeObject1)
//        teacherInfoInfoList.add(collegeObject2)
//        teacherInfoInfoList.add(collegeObject3)
//
//        val courseObject=CourseListModel(R.drawable.slider,"Course name","Course price")
//        val courseObject1=CourseListModel(R.drawable.slider1,"Physics","800")
//        val courseObject2=CourseListModel(R.drawable.slider1,"Mathematics","900")
//        val courseObject3=CourseListModel(R.drawable.slider,"English","2000")
//        courseInfoInfoList.add(courseObject)
//        courseInfoInfoList.add(courseObject1)
//        courseInfoInfoList.add(courseObject2)
//        courseInfoInfoList.add(courseObject3)
//
//
//
//        initRecyclerTeacher()
//        initRecyclerCourse()
//        imageSwitcher()
//        return view
//    }
//
//    private fun initRecyclerTeacher() {
//        recyclerAdapter = TeacherListAdapter(activity as Context, teacherInfoInfoList)
//        recyclerView.adapter = recyclerAdapter
//        recyclerView.layoutManager=LinearLayoutManager(activity as Context, LinearLayoutManager.HORIZONTAL,false)
//
//    }
//
//    private fun initRecyclerCourse() {
//        recyclerAdapterCourse = CourseAdapter(activity as Context, courseInfoInfoList)
//        recyclerViewCourse.adapter = recyclerAdapterCourse
//        recyclerViewCourse.layoutManager=LinearLayoutManager(activity as Context, LinearLayoutManager.HORIZONTAL,false)
//
//    }
//
//    private fun imageSwitcher(){
//        val imageAdapter=PagerAdapterTwo(activity as Context)
//        viewPager.adapter=imageAdapter
//
//        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)
//            {
//
//            }
//
//            override fun onPageSelected(position: Int) {
//
//                when(viewPager.currentItem){
//                    0->{
//                        img1.setImageResource(R.drawable.indicator_circle_black)
//                        img2.setImageResource(R.drawable.indicator_circle)
//                        img3.setImageResource(R.drawable.indicator_circle)
//                        img4.setImageResource(R.drawable.indicator_circle)
//                        img5.setImageResource(R.drawable.indicator_circle)
//                        img6.setImageResource(R.drawable.indicator_circle)
//                    }
//                    1->{
//                        img1.setImageResource(R.drawable.indicator_circle)
//                        img2.setImageResource(R.drawable.indicator_circle_black)
//                        img3.setImageResource(R.drawable.indicator_circle)
//                        img4.setImageResource(R.drawable.indicator_circle)
//                        img5.setImageResource(R.drawable.indicator_circle)
//                        img6.setImageResource(R.drawable.indicator_circle)
//                    }
//                    2->{
//                        img1.setImageResource(R.drawable.indicator_circle)
//                        img2.setImageResource(R.drawable.indicator_circle)
//                        img3.setImageResource(R.drawable.indicator_circle_black)
//                        img4.setImageResource(R.drawable.indicator_circle)
//                        img5.setImageResource(R.drawable.indicator_circle)
//                        img6.setImageResource(R.drawable.indicator_circle)
//                    }
//                    3->{
//                        img1.setImageResource(R.drawable.indicator_circle)
//                        img2.setImageResource(R.drawable.indicator_circle)
//                        img3.setImageResource(R.drawable.indicator_circle)
//                        img4.setImageResource(R.drawable.indicator_circle_black)
//                        img5.setImageResource(R.drawable.indicator_circle)
//                        img6.setImageResource(R.drawable.indicator_circle)
//                    }
//                    4->{
//                        img1.setImageResource(R.drawable.indicator_circle)
//                        img2.setImageResource(R.drawable.indicator_circle)
//                        img3.setImageResource(R.drawable.indicator_circle)
//                        img4.setImageResource(R.drawable.indicator_circle)
//                        img5.setImageResource(R.drawable.indicator_circle_black)
//                        img6.setImageResource(R.drawable.indicator_circle)
//                    }
//                    5->{
//                        img1.setImageResource(R.drawable.indicator_circle)
//                        img2.setImageResource(R.drawable.indicator_circle)
//                        img3.setImageResource(R.drawable.indicator_circle)
//                        img4.setImageResource(R.drawable.indicator_circle)
//                        img5.setImageResource(R.drawable.indicator_circle)
//                        img6.setImageResource(R.drawable.indicator_circle_black)
//                    }
//                }
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//
//            }
//
//        })
//    }
