package com.bcebhagalpur.welcomeslider.bodyfragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.Swipe
import com.bcebhagalpur.welcomeslider.adapter.TEacherAdapter
import com.bcebhagalpur.welcomeslider.model.Teacherlist
import java.util.*

class TeacherFragment :Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: TEacherAdapter
    private val teacherInfoInfoList = arrayListOf<Teacherlist>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_teacher, container, false)
        recyclerView=view.findViewById(R.id.teachers_list)
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
        recyclerAdapter = TEacherAdapter(activity as Context, teacherInfoInfoList)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager=
            LinearLayoutManager(activity as Context, LinearLayoutManager.VERTICAL,false)
        val item=object :Swipe(activity as Context,0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                var fromPosition=viewHolder.adapterPosition
                var toPosition=viewHolder.adapterPosition
                Collections.swap(teacherInfoInfoList,fromPosition,toPosition)
               // recyclerView.adapter!!.notifyItemMoved(fromPosition,toPosition)
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Toast.makeText(activity as Context,"Senorita",Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper=ItemTouchHelper(item)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return view
    }

}
