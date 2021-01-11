package com.bcebhagalpur.welcomeslider.viewModel

import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.model.ExploreChildModel
import java.util.*

object ExploreChildViewModel{

    private val random = Random()

    private val titles =  arrayListOf( "Aviator", "Now you can See me", "God Father", "Mission Impossible", "3 idiots")

    private fun randomTitle() : String{
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    private fun randomImage() : Int{
        return R.drawable.slider1
    }

    fun getChildren(count : Int) : List<ExploreChildModel>{
        val children = mutableListOf<ExploreChildModel>()
        repeat(count){
            val child = ExploreChildModel(randomImage(), randomTitle())
            children.add(child)
        }
        return children
    }


}