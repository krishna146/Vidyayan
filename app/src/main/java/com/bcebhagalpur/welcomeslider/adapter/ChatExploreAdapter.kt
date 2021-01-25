package com.bcebhagalpur.welcomeslider.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.activity.VidyayanChatingActivity
import com.bcebhagalpur.welcomeslider.model.ChatExplore
import com.bcebhagalpur.welcomeslider.model.CourseListModel

class ChatExploreAdapter(private val context: Context, private val itemList: ArrayList<ChatExplore>) : RecyclerView.Adapter<ChatExploreAdapter.ChatExploreHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ChatExploreHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_explore, parent, false)
        return ChatExploreHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ChatExploreHolder, position: Int) {

        val chatlist = itemList[position]
       holder.txtuserName.text=chatlist.teacher_name
        holder.imgteacher.setImageResource(chatlist.teacher_profile)
        holder.cardView.setOnClickListener {
            val intent= Intent(context, VidyayanChatingActivity::class.java)
            context.startActivity(intent)
        }
          }

    class ChatExploreHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtuserName: TextView = view.findViewById(R.id.txtChatUserName)
        val imgteacher: ImageView = view.findViewById(R.id.img_teacher_pic)
        val cardView:CardView=view.findViewById(R.id.card_explore)


    }
}

