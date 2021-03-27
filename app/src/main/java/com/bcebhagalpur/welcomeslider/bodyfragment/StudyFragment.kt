package com.bcebhagalpur.welcomeslider.bodyfragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
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
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.TestActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class StudyFragment : Fragment() {

    private lateinit var mathematics:Button
    private lateinit var science:Button
    private lateinit var socialScience:Button
    private lateinit var hindi:Button
    private lateinit var computerScience:Button
    private lateinit var english:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_study, container, false)

        mathematics=view.findViewById(R.id.mathematics)
        science=view.findViewById(R.id.science)
        socialScience=view.findViewById(R.id.socialScience)
        hindi=view.findViewById(R.id.hindi)
        computerScience=view.findViewById(R.id.computerScience)
        english=view.findViewById(R.id.english)

        val subjectList= arrayOf(mathematics,science,computerScience,hindi,english,socialScience)
        val userId= FirebaseAuth.getInstance().currentUser!!.uid
        val student= FirebaseDatabase.getInstance().reference.child("STUDENT")
        val anotherChildStudent=student.child(userId)
        anotherChildStudent.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val studentClass=snapshot.child("studentClass").value.toString()
                    if(studentClass=="1st" || studentClass=="2nd" || studentClass=="3rd" || studentClass=="4th" || studentClass=="5th"
                        || studentClass=="6th"  || studentClass=="7th"  || studentClass=="8th"  || studentClass=="9th"
                        || studentClass=="10th")
                    {
                        for (i in subjectList){
                            i.setOnClickListener {
                                val intent=Intent(context,TestActivity::class.java)
                                intent.putExtra("studentSubject",i.text.toString())
                                intent.putExtra("studentClass",studentClass.toString())
                                startActivity(intent)
                                Toast.makeText(context,studentClass,Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else if(studentClass=="11th" || studentClass=="12th")
                    {
                        science.text="Physics"
                        socialScience.text="Chemistry"
                        hindi.text="Biology"
                        for (i in subjectList){
                            i.setOnClickListener {
                                val intent=Intent(context,TestActivity::class.java)
                                intent.putExtra(i.text.toString(),i.text.toString())
                                startActivity(intent)
                                Toast.makeText(context,studentClass,Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {} })

        return view
    }


}