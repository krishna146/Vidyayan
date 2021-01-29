package com.bcebhagalpur.welcomeslider.bodyfragment

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.Swipe
import com.bcebhagalpur.welcomeslider.activity.BlankActivity2
import com.bcebhagalpur.welcomeslider.adapter.*
import com.bcebhagalpur.welcomeslider.model.Schedule
import com.bcebhagalpur.welcomeslider.model.StudentAround
import com.bcebhagalpur.welcomeslider.model.Syllabus
import java.util.*


class ExploreTeacherFragment :Fragment() {
    private lateinit var img1: ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var img4: ImageView
    private lateinit var img5: ImageView
    private lateinit var img6: ImageView

    private lateinit var viewPager: ViewPager

      lateinit var recyclerView_one:RecyclerView
      private lateinit var scheduleAdapter:ScheduleAdapter
      private val schedule_list= arrayListOf<Schedule>()

      lateinit var recyclerView_three:RecyclerView
      private lateinit var studentAroundAdapter: StudentAroundAdapter
      private val student_aroundList= arrayListOf<StudentAround>()

      lateinit var recyclerView_two:RecyclerView
      private lateinit var syllabusAdapter: SyllabusAdapter
      private val syllabus_list= arrayListOf<Syllabus>()

      override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
      ): View? {
          // Inflate the layout for this fragment
          val view= inflater.inflate(R.layout.fragment_explore_teacher, container, false)
          img1=view.findViewById(R.id.indicator1)
          img2=view.findViewById(R.id.indicator2)
          img3=view.findViewById(R.id.indicator3)
          img4=view.findViewById(R.id.indicator4)
          img5=view.findViewById(R.id.indicator5)
          img6=view.findViewById(R.id.indicator6)

          viewPager=view.findViewById(R.id.viewPager)

          recyclerView_one=view.findViewById(R.id.recyclerView1)
          val schedule_object1=Schedule(R.drawable.tp,"Ashutosh Kumar","10A.M-12A.M","Online","BCE,Sabour,Bhagalpur")
          val schedule_object2=Schedule(R.drawable.tp,"Ashutosh Kumar","10A.M-12A.M","Online","BCE,Sabour,Bhagalpur")
          val schedule_object3=Schedule(R.drawable.tp,"Ashutosh Kumar","10A.M-12A.M","Online","BCE,Sabour,Bhagalpur")
          val schedule_object4=Schedule(R.drawable.tp,"Ashutosh Kumar","10A.M-12A.M","Online","BCE,Sabour,Bhagalpur")
          val schedule_object5=Schedule(R.drawable.tp,"Ashutosh Kumar","10A.M-12A.M","Online","BCE,Sabour,Bhagalpur")
          val schedule_object6=Schedule(R.drawable.tp,"Ashutosh Kumar","10A.M-12A.M","Online","BCE,Sabour,Bhagalpur")
          schedule_list.add(schedule_object1)
          schedule_list.add(schedule_object2)
          schedule_list.add(schedule_object3)
          schedule_list.add(schedule_object4)
          schedule_list.add(schedule_object5)
          schedule_list.add(schedule_object6)
          scheduleAdapter = ScheduleAdapter(activity as Context, schedule_list)
          recyclerView_one.adapter = scheduleAdapter
          recyclerView_one.layoutManager=
              LinearLayoutManager(activity as Context, LinearLayoutManager.HORIZONTAL,false)

          recyclerView_two=view.findViewById(R.id.recyclerView2)
          val syllabus_object1= Syllabus(R.drawable.tp,"Computer")
          val syllabus_object2=Syllabus(R.drawable.tp,"Computer")
          val syllabus_object3=Syllabus(R.drawable.tp,"Computer")
          val syllabus_object4= Syllabus(R.drawable.tp,"Computer")
          val syllabus_object5=Syllabus(R.drawable.tp,"Computer")
          val syllabus_object6= Syllabus(R.drawable.tp,"Computer")
          syllabus_list.add(syllabus_object1)
          syllabus_list.add(syllabus_object2)
          syllabus_list.add(syllabus_object3)
          syllabus_list.add(syllabus_object4)
          syllabus_list.add(syllabus_object5)
          syllabus_list.add(syllabus_object6)
          syllabusAdapter= SyllabusAdapter(activity as Context,syllabus_list)
          recyclerView_two.adapter=syllabusAdapter
          recyclerView_two.layoutManager= LinearLayoutManager(activity as Context, LinearLayoutManager.HORIZONTAL,false)

          recyclerView_three=view.findViewById(R.id.recyclerView3)
          val studentAround_Object1=StudentAround(R.drawable.tp,"Ashutosk kumar","Class10","BCE,Sabour,Bhagalpur","Prefer online mode of education","Want to study physich and math","Available for study between 6-9 in evening","Can pay up to Rs 1000")
          val studentAround_Object2=StudentAround(R.drawable.tp,"Ashutosk kumar","Class 10","BCE,Sabour,Bhagalpur","Prefer online mode of education","Want to study physich and math","Available for study between 6-9 in evening","Can pay up to Rs 1000")
          val studentAround_Object3=StudentAround(R.drawable.tp,"Ashutosk kumar","Class 10","BCE,Sabour,Bhagalpur","Prefer online mode of education","Want to study physich and math","Available for study between 6-9 in evening","Can pay up to Rs 1000")
          val studentAround_Object4=StudentAround(R.drawable.tp,"Ashutosk kumar","Class 10","BCE,Sabour,Bhagalpur","Prefer online mode of education","Want to study physich and math","Available for study between 6-9 in evening","Can pay up to Rs 1000")
          val studentAround_Object5=StudentAround(R.drawable.tp,"Ashutosk kumar","Class 10","BCE,Sabour,Bhagalpur","Prefer online mode of education","Want to study physich and math","Available for study between 6-9 in evening","Can pay up to Rs 1000")
          val studentAround_Object6=StudentAround(R.drawable.tp,"Ashutosk kumar","Class 10","BCE,Sabour,Bhagalpur","Prefer online mode of education","Want to study physich and math","Available for study between 6-9 in evening","Can pay up to Rs 1000")
          val studentAround_Object7=StudentAround(R.drawable.tp,"Ashutosk kumar","Class 10","BCE,Sabour,Bhagalpur","Prefer online mode of education","Want to study physich and math","Available for study between 6-9 in evening","Can pay up to Rs 1000")
            student_aroundList.add(studentAround_Object1)
          student_aroundList.add(studentAround_Object2)
          student_aroundList.add(studentAround_Object3)
          student_aroundList.add(studentAround_Object4)
          student_aroundList.add(studentAround_Object5)
          student_aroundList.add(studentAround_Object6)
          student_aroundList.add(studentAround_Object7)
          studentAroundAdapter= StudentAroundAdapter(activity as Context,student_aroundList)
          recyclerView_three.adapter=studentAroundAdapter
          recyclerView_three.layoutManager= LinearLayoutManager(activity as Context, LinearLayoutManager.VERTICAL,false)
          imageSwitcher()
          val item=object : Swipe(activity as Context,0, ItemTouchHelper.LEFT){
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
                  Toast.makeText(activity as Context,"Senorita", Toast.LENGTH_SHORT).show()
                  startActivity(Intent(activity as Context,BlankActivity2::class.java))
                 studentAroundAdapter.notifyItemChanged(viewHolder.adapterPosition)
                  var fromPosition=viewHolder.adapterPosition
                    var toPosition=viewHolder.adapterPosition
                   Collections.swap(student_aroundList,fromPosition,toPosition)
                    recyclerView_three.adapter!!.notifyItemMoved(toPosition,fromPosition)

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
           val itemTouchHelper=ItemTouchHelper(item)
           itemTouchHelper.attachToRecyclerView(recyclerView_three)

          return view
      }
    private fun imageSwitcher(){
        val imageAdapter= PagerAdapterThree(activity as Context)
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