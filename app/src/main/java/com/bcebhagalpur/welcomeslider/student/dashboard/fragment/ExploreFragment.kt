package com.bcebhagalpur.welcomeslider.student.dashboard.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.adapter.ExploreTeacherListAdapter
import com.bcebhagalpur.welcomeslider.student.dashboard.model.ExploreTeacherListModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.collections.ArrayList

class ExploreFragment : Fragment() {

    private lateinit var recyclerTeacher1: RecyclerView
    private lateinit var recyclerTeacher2: RecyclerView
    private lateinit var recyclerTeacher3: RecyclerView
    private lateinit var recyclerTeacher4: RecyclerView
    private lateinit var exploreTeacherListAdapter: ExploreTeacherListAdapter
    private val exploreTeacherListModel = ArrayList<ExploreTeacherListModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    {

        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        recyclerTeacher1 = view.findViewById(R.id.recyclerTeacher1)
        recyclerTeacher2 = view.findViewById(R.id.recyclerTeacher2)
        recyclerTeacher3 = view.findViewById(R.id.recyclerTeacher3)
        recyclerTeacher4 = view.findViewById(R.id.recyclerTeacher4)

        exploreTeacherListAdapter = ExploreTeacherListAdapter(activity as Context, exploreTeacherListModel)

        val layoutManager1 = LinearLayoutManager(activity as Context)
        layoutManager1.reverseLayout
        layoutManager1.stackFromEnd
        recyclerTeacher1.layoutManager = layoutManager1
        recyclerTeacher1.adapter = exploreTeacherListAdapter
        recyclerTeacher1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        })

        val layoutManager2 = LinearLayoutManager(activity as Context)
        layoutManager2.reverseLayout
        layoutManager2.stackFromEnd
        recyclerTeacher2.layoutManager = layoutManager2
        recyclerTeacher2.adapter = exploreTeacherListAdapter
        recyclerTeacher2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        })

        val layoutManager3 = LinearLayoutManager(activity as Context)
        layoutManager3.reverseLayout
        layoutManager3.stackFromEnd
        recyclerTeacher3.layoutManager = layoutManager3
        recyclerTeacher3.adapter = exploreTeacherListAdapter
        recyclerTeacher3.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        })

        val layoutManager4 = LinearLayoutManager(activity as Context)
        layoutManager4.reverseLayout
        layoutManager4.stackFromEnd
        recyclerTeacher4.layoutManager = layoutManager4
        recyclerTeacher4.adapter = exploreTeacherListAdapter
        recyclerTeacher4.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        })


        initRecyclerTeacher()
        initRecyclerTeacher()
        initRecyclerTeacher()
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
                      if(studentClass=="1st" || studentClass=="2nd" || studentClass=="3rd" || studentClass=="4th" || studentClass=="5th")
                      {
                          getUser(studentCity,"1 to 5th",recyclerTeacher1)
                          getUser(studentCity,"1 to 8th",recyclerTeacher2)
                          getUser(studentCity,"1 to 10th",recyclerTeacher3)
                          getUser(studentCity,"1 to 12th",recyclerTeacher4)
                      }
                  }
            }
           override fun onCancelled(error: DatabaseError) {} })
    }




    private fun getUser(studentCity: String, studentClass: String,recyclerTeacher:RecyclerView) {
        val child = FirebaseDatabase.getInstance().reference.child("TEACHERS").child(studentCity).child(studentClass)
        child.addListenerForSingleValueEvent(object : ValueEventListener
        {

            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot)
            {
                val userList = ArrayList<ExploreTeacherListModel>()
                if (snapshot.exists()) {
                    for (p0 in snapshot.children) {
                        val p1 = p0.getValue(ExploreTeacherListModel::class.java)
                        userList.add(0, p1!!)
                    }
                    try {
                        exploreTeacherListAdapter = ExploreTeacherListAdapter(activity as Context, userList)
                        if (snapshot.exists()) {
                            val maxId = snapshot.childrenCount
                            val maxInt = maxId.toInt()
                            for (i in 0..maxInt) {
                                exploreTeacherListAdapter.notifyItemInserted(i)
                                recyclerTeacher.smoothScrollToPosition(i)
                            }

                        }

                    }catch (e:NullPointerException){
                        e.printStackTrace()
                    }
                    recyclerTeacher.adapter = exploreTeacherListAdapter

                }
//                                      else {
//                                          Toast.makeText(context,"Item not found",Toast.LENGTH_SHORT).show()
//                                      }
            }
        }
        )
    }
}


//        val item = object : Swipe(activity as Context, 0, ItemTouchHelper.LEFT) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                //  var fromPosition=viewHolder.adapterPosition
//                //  var toPosition=viewHolder.adapterPosition
//                //  Collections.swap(teacherInfoInfoList,fromPosition,toPosition)
//                //  recyclerView.adapter!!.notifyItemMoved(toPosition,fromPosition)
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                Toast.makeText(activity as Context, "Senorita", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(activity as Context, BlankActivity2::class.java))
//                exploreTeacherListAdapter.notifyItemChanged(viewHolder.adapterPosition)
//                val fromPosition = viewHolder.adapterPosition
//                val toPosition = viewHolder.adapterPosition
//                Collections.swap(exploreTeacherListModel, fromPosition, toPosition)
//                recyclerTeacher.adapter!!.notifyItemMoved(toPosition, fromPosition)
//
//            }
//
//            override fun onChildDraw(
//                c: Canvas,
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                dX: Float,
//                dY: Float,
//                actionState: Int,
//                isCurrentlyActive: Boolean
//            ) {
//
//
//            }
//        }
//        val itemTouchHelper = ItemTouchHelper(item)
//        itemTouchHelper.attachToRecyclerView(recyclerTeacher)
