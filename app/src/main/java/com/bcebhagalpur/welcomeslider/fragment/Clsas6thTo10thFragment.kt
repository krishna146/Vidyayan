package com.bcebhagalpur.welcomeslider.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bcebhagalpur.welcomeslider.R
import kotlinx.android.synthetic.main.fragment_class6th_to10th.*
import kotlinx.android.synthetic.main.fragment_class6th_to10th.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Claas6thTo10thFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Claas6thTo10thFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view = inflater.inflate(R.layout.fragment_class6th_to10th, container, false)
        view.txtCBSE.setOnClickListener(){
            txtCBSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtICSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtSTATE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        view.txtICSE.setOnClickListener(){
            txtICSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtCBSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtSTATE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
        }
        view.txtSTATE.setOnClickListener(){
            txtSTATE.setBackgroundResource(R.drawable.rounded_corners_imagebutton2)
            txtICSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
            txtCBSE.setBackgroundResource(R.drawable.rounded_corners_imagebutton)
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
         * @return A new instance of fragment Claas6thTo10thFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Claas6thTo10thFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}