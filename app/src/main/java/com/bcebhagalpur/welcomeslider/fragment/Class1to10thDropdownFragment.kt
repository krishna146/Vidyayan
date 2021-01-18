package com.bcebhagalpur.welcomeslider.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.bcebhagalpur.welcomeslider.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Class1to10thDropdownFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Class1to10thDropdownFragment : Fragment() {
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
        val view =inflater.inflate(R.layout.fragment_class1to10th_dropdown, container, false)

        val autoCompleteTextViewBoard = view.findViewById<AutoCompleteTextView>(R.id.AutoCompleteTextViewBoard)
        val menuItems = listOf("Select Your Board",  "CBSE","ICSE", "STATE")
        val adapter = ArrayAdapter(activity as Context, R.layout.list_item, menuItems)
        autoCompleteTextViewBoard.setAdapter(adapter)

        // set default menu item
        autoCompleteTextViewBoard.setText("Select Your Board", false)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Class1to10thDropdownFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Class1to10thDropdownFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}