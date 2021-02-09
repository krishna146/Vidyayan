package com.bcebhagalpur.welcomeslider.student.dashboard.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.TeacherDemoVideoActivity
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.TeacherDetailActivity
import com.bcebhagalpur.welcomeslider.student.dashboard.model.ExploreTeacherListModel
import com.squareup.picasso.Picasso

class ExploreTeacherListAdapter(private val context: Context, private val itemList: ArrayList<ExploreTeacherListModel>) : RecyclerView.Adapter<ExploreTeacherListAdapter.StatusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.explore_student_teacher_list, parent, false)
        return StatusViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {

        val subject = itemList[position]
       Picasso.get().load(subject.teacherImage).into(holder.teacherImage)
     holder.teacherName.text=subject.teacherName
        holder.teacherQualification.text=subject.qualification
        holder.teacherSubject.text=subject.subject
        holder.teacherTiming.text=subject.timing
        holder.teacherGrade.text=subject.teacherClass
        holder.rating.numStars=5
        holder.linearLayout.setOnClickListener {
            val intent=Intent(context,TeacherDetailActivity::class.java)
            intent.putExtra("teacherClass",subject.teacherClass)
            intent.putExtra("college",subject.college)
            intent.putExtra("language",subject.language)
            intent.putExtra("mobileNumber",subject.mobileNumber)
            intent.putExtra("mode",subject.mode)
            intent.putExtra("price",subject.price)
            intent.putExtra("qualification",subject.qualification)
            intent.putExtra("status",subject.status)
            intent.putExtra("subject",subject.subject)
            intent.putExtra("teacherAddress",subject.teacherAddress)
            intent.putExtra("teacherCity",subject.teacherCity)
            intent.putExtra("teacherDob",subject.teacherDob)
            intent.putExtra("teacherEmail",subject.teacherEmail)
            intent.putExtra("teacherGender",subject.teacherGender)
            intent.putExtra("teacherName",subject.teacherName)
            intent.putExtra("timing",subject.timing)
            intent.putExtra("userId",subject.userId)
           context.startActivity(Intent(context, TeacherDetailActivity::class.java))
        }

    }

    class StatusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val teacherImage: ImageView = view.findViewById(R.id.imgTeacher)
        val teacherName: TextView= view.findViewById(R.id.txtTeacherName)
        val teacherGrade:TextView=view.findViewById(R.id.txtTeacherGrade)
        val teacherTiming:TextView=view.findViewById(R.id.txtTeacherTime)
        val teacherQualification: TextView = view.findViewById(R.id.txtQualification)
        val teacherSubject: TextView= view.findViewById(R.id.txtTeacherSubject)
        val rating: RatingBar = view.findViewById(R.id.rating)
        val linearLayout: LinearLayout = view.findViewById(R.id.llt)
    }

val swipe=object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        context.startActivity(Intent(context,TeacherDemoVideoActivity::class.java))
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
        c.clipRect(0f, viewHolder.itemView.top.toFloat(),
            dX, viewHolder.itemView.bottom.toFloat())
            c.drawColor(Color.RED)
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
  }
}
