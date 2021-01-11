package com.bcebhagalpur.welcomeslider.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bcebhagalpur.welcomeslider.R
import kotlinx.android.synthetic.main.fragment_class13th.*
import kotlinx.android.synthetic.main.fragment_class13th.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Class13thFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_class13th, container, false)

        view.txtJEE.setOnClickListener() {
            txtJEE.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtNEET.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        view.txtNEET.setOnClickListener() {
            txtNEET.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtJEE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)

        }

        return view
    }


}
