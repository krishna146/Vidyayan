package com.bcebhagalpur.welcomeslider.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.starter.activity.ChooseClassActivity
import com.bcebhagalpur.welcomeslider.student.starter.activity.RegistrationActivity
import kotlinx.android.synthetic.main.fragment_class13th.*
import kotlinx.android.synthetic.main.fragment_class13th.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Class13thFragment : Fragment() {
    var chooseClassActivity = ChooseClassActivity()
    lateinit var targetExam:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_class13th, container, false)

        view.txtJEE.setOnClickListener() {
            targetExam = txtJEE.text.toString()
            txtJEE.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtNEET.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        view.txtNEET.setOnClickListener() {
            targetExam = txtNEET.text.toString()
            txtNEET.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtJEE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)

        }
        val studentClass= (activity as ChooseClassActivity?)?.getResult()

        view.btnStartLearning.setOnClickListener {
            try {
                val intent = Intent(activity, RegistrationActivity::class.java)
                intent.putExtra("targetExam", targetExam)
                intent.putExtra("studentClass", studentClass)
                startActivity(intent)
            }catch (e:UninitializedPropertyAccessException){
                Toast.makeText(context,"please select target exam",Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }



}
