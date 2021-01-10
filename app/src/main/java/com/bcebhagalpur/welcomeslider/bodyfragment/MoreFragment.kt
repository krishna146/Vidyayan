package com.bcebhagalpur.welcomeslider.bodyfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.bcebhagalpur.welcomeslider.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_more.*

class MoreFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<RelativeLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_more, container, false)
//        initView()
//        slideUpDownBottomSheet()
return view
    }

//    private fun initView() {
//
//        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
//        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//
//            }
//
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                when (newState) {
//                    BottomSheetBehavior.STATE_COLLAPSED -> {
//                    }
//                    BottomSheetBehavior.STATE_HIDDEN -> {
//
//                    }
//                    BottomSheetBehavior.STATE_EXPANDED -> {
//                    }
//                    BottomSheetBehavior.STATE_DRAGGING -> {
//
//                    }
//                    BottomSheetBehavior.STATE_SETTLING -> {
//
//                    }
//                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
//
//                    }
//                }
//            }
//        })
//    }
//
//    private fun slideUpDownBottomSheet() {
//        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
//            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//
//        } else {
//            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//        }
//    }

}