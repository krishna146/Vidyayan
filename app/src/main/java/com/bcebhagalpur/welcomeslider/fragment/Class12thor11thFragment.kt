package com.bcebhagalpur.welcomeslider.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.starter.activity.ChooseClassActivity
import com.bcebhagalpur.welcomeslider.student.starter.activity.RegistrationActivity
import kotlinx.android.synthetic.main.fragment_class12thor11th.*
import kotlinx.android.synthetic.main.fragment_class12thor11th.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Class12thor11thFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var studentBoard: String
    lateinit var studentStream: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_class12thor11th, container, false)
        view.txtCBSE.setOnClickListener(){
            studentBoard = txtCBSE.text.toString()
            txtCBSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtICSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtSTATE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        view.txtICSE.setOnClickListener(){
            studentBoard = txtICSE.text.toString()
            txtICSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtCBSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtSTATE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        view.txtSTATE.setOnClickListener(){
            studentBoard = txtSTATE.text.toString()
            txtSTATE.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtICSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtCBSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        view.txtSciencePCB.setOnClickListener(){
            studentStream = txtSciencePCB.text.toString()
            txtSciencePCB.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtSciencePCM.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtCommerce.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        view.txtSciencePCM.setOnClickListener(){
            studentStream = txtSciencePCM.text.toString()
            txtSciencePCM.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtSciencePCB.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtCommerce.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        view.txtCommerce.setOnClickListener(){
            studentStream = txtCommerce.text.toString()
            txtCommerce.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtSciencePCM.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtSciencePCB.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        var studentClass= (activity as ChooseClassActivity?)?.getResult()
        view.btnStartLearning.setOnClickListener(){
            val intent = Intent(activity, RegistrationActivity::class.java)
            intent.putExtra("studentClass",studentClass )
            intent.putExtra("studentBoard", studentBoard)
            intent.putExtra("studentStream", studentStream)
            startActivity(intent)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Class12thFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Class12thor11thFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}