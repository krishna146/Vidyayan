package com.bcebhagalpur.welcomeslider.viewModel

import com.bcebhagalpur.welcomeslider.model.ExploreChildModel
import com.bcebhagalpur.welcomeslider.model.ExploreParentModel
import java.util.*

object ExploreParentViewModel{
    private val random = Random()

    private val titles =  arrayListOf( "Now Playing", "Classic", "Comedy", "Thriller", "Action", "Horror", "TV Series")

    private fun randomTitle() : String{
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    private fun randomChildren() : List<ExploreChildModel>{
        return ExploreChildViewModel.getChildren(20)
    }

    fun getParents(count : Int) : List<ExploreParentModel>{
        val parents = mutableListOf<ExploreParentModel>()
        repeat(count){
            val parent = ExploreParentModel(randomTitle(), randomChildren())
            parents.add(parent)
        }
        return parents
    }
}