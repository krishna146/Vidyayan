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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.fragment_explore.*
import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList


class ExploreFragment : Fragment() {

    private lateinit var recyclerTeacher: RecyclerView
    private lateinit var exploreTeacherListAdapter: ExploreTeacherListAdapter
    private val exploreTeacherListModel = ArrayList<ExploreTeacherListModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        recyclerTeacher = view.findViewById(R.id.recyclerTeacher)

        val layoutManager = LinearLayoutManager(activity as Context)
        layoutManager.reverseLayout
        layoutManager.stackFromEnd
       recyclerTeacher.layoutManager = layoutManager

        exploreTeacherListAdapter = ExploreTeacherListAdapter(activity as Context, exploreTeacherListModel)
        recyclerTeacher.adapter = exploreTeacherListAdapter
       recyclerTeacher.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        })


        initRecyclerTeacher()

        return view

    }

    private fun initRecyclerTeacher() {

        val userId= FirebaseAuth.getInstance().currentUser!!.uid

        val student=FirebaseDatabase.getInstance().reference.child("STUDENT")
        val anotherChildStudent=student.child(userId)
       anotherChildStudent.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                  if (snapshot.exists()){
                      val studentCity = snapshot.child("studentCity").value.toString()
                      val studentClass=snapshot.child("studentClass").value.toString()
                      val child = FirebaseDatabase.getInstance().reference.child("TEACHERS").orderByChild(studentCity)
//                      val anotherChild = child.child(studentCity)

//                      val anotherChild1=anotherChild.child(studentClass)
                      child.addListenerForSingleValueEvent(object : ValueEventListener {
                          override fun onCancelled(error: DatabaseError) {

                          }
                          override fun onDataChange(snapshot: DataSnapshot) {
                              if (snapshot.exists()) {
                                  for (p0 in snapshot.children) {
                                      for (s0 in p0.children){
                                          val p1 = s0.getValue(ExploreTeacherListModel::class.java)
                                          exploreTeacherListModel.add(0, p1!!)
                                      }
                                  }
                                  exploreTeacherListAdapter =
                                      ExploreTeacherListAdapter(activity as Context, exploreTeacherListModel)
                                  if (snapshot.exists()) {
                                      val maxId = snapshot.childrenCount
                                      val maxInt = maxId.toInt()
                                      for (i in 0..maxInt) {
                                          exploreTeacherListAdapter.notifyItemInserted(i)
                                          recyclerTeacher.smoothScrollToPosition(i)
                                      }

                                  }

                                  recyclerTeacher.adapter = exploreTeacherListAdapter

                              } else {
                                  Toast.makeText(context,"Item not found",Toast.LENGTH_SHORT).show()
                              }
                          }

                      })
                  }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })



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
}
