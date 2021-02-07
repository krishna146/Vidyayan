package com.bcebhagalpur.welcomeslider.adapter

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.activity.ViewFullImageActivity
import com.bcebhagalpur.welcomeslider.model.Chat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ChatAdapter (val context: Context, val chatList: List<Chat>,val imageUrl:String):
    RecyclerView.Adapter<ChatAdapter.ViewHolder?>() {
    var firebaseUser: FirebaseUser?= FirebaseAuth.getInstance().currentUser

    override fun onCreateViewHolder(parent: ViewGroup, position: Int):ViewHolder {
        if (position==1){
            val view= LayoutInflater.from(context).inflate(R.layout.rightchatfile,parent,false)
            return ViewHolder(view)
        }else{
            val view= LayoutInflater.from(context).inflate(R.layout.leftchatfile,parent,false)
            return ViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat: Chat = chatList[position]

        Picasso.get().load(imageUrl).into(holder.imgMessageItemLeftProfile)
        //image message
        if (chat.getMessage().equals("sent you an image.") && !chat.getUrl().equals("")) {
            //image message -right side
            if (chat.getSender().equals(firebaseUser!!.uid)) {
                holder.txtShowTextMessage!!.visibility = View.GONE
                holder.rightImageView!!.visibility = View.VISIBLE
                Picasso.get().load(chat.getUrl()).into(holder.rightImageView)

                holder.rightImageView!!.setOnClickListener {
                    val options= arrayOf<CharSequence>(
                        "view full image",
                        "delete image",
                        " cancel"
                    )
                    var builder: AlertDialog.Builder= AlertDialog.Builder(holder.itemView.context)
                    builder.setTitle("What do you want?")
                    builder.setItems(options, DialogInterface.OnClickListener({
                            dialog, which ->
                        if (which==0){
                            val intent= Intent(context,
                                ViewFullImageActivity::class.java)
                            intent.putExtra("url",chat.getUrl())
                            context.startActivity(intent)
                        }
                        else if(which==1)
                        {
                            deleteSentMessage(position,holder)
                        }
                    }))
                    builder.show()
                }
            }
            //image message -left side
            else if (!chat.getSender().equals(firebaseUser!!.uid)) {
                holder.txtShowTextMessage!!.visibility = View.GONE
                holder.leftImageView!!.visibility = View.VISIBLE
                Picasso.get().load(chat.getUrl()).into(holder.leftImageView)
                holder.leftImageView!!.setOnClickListener {
                    val options= arrayOf<CharSequence>(
                        "view full image",
                        "delete image",
                        " cancel"
                    )
                    var builder: AlertDialog.Builder= AlertDialog.Builder(holder.itemView.context)
                    builder.setTitle("What do you want?")
                    builder.setItems(options, DialogInterface.OnClickListener({
                            dialog, which ->
                        if (which==0){
                            val intent= Intent(context,
                                ViewFullImageActivity::class.java)
                            intent.putExtra("url",chat.getUrl())
                            context.startActivity(intent)
                        }
                        else if(which==1)
                        {
                            deleteSentMessage(position,holder)
                        }
                    }))
                    builder.show()
                }
            }
        }
        //text message
        else {
            holder.txtShowTextMessage!!.text = chat.getMessage()
            holder.txtShowTextMessage!!.setOnClickListener {
                val options= arrayOf<CharSequence>(

                    "delete message",
                    " cancel"
                )
                var builder: AlertDialog.Builder= AlertDialog.Builder(holder.itemView.context)
                builder.setTitle("What do you want?")
                builder.setItems(options, DialogInterface.OnClickListener({
                        dialog, which ->
                    if(which==0)
                    {
                        deleteSentMessage(position,holder)
                    }
                }))
                builder.show()
            }
        }
        //sent and seen msg
        if (position == chatList.size - 1) {
            if (chat.isIsSeen()!!) {
                holder.textSeen!!.text = "seen"
                if (chat.getMessage().equals("sent you an image.") && !chat.getUrl().equals("")) {
                    val lp: RelativeLayout.LayoutParams? =
                        holder.textSeen!!.layoutParams as RelativeLayout.LayoutParams
                    lp!!.setMargins(0, 245, 10, 0)
                    holder.textSeen!!.layoutParams = lp
                }
            } else {
                holder.textSeen!!.text = "sent"
                if (chat.getMessage().equals("sent you an image.") && !chat.getUrl().equals("")) {
                    val lp: RelativeLayout.LayoutParams? =
                        holder.textSeen!!.layoutParams as RelativeLayout.LayoutParams
                    lp!!.setMargins(0, 245, 10, 0)
                    holder.textSeen!!.layoutParams = lp
                }
            }
        } else {
            holder.textSeen!!.visibility = View.GONE
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgMessageItemLeftProfile:ImageView? =
            view.findViewById(R.id.msg_left_profile)
        var txtShowTextMessage: TextView? = view.findViewById(R.id.txtShowTextMessage)
        var leftImageView: ImageView? = view.findViewById(R.id.leftImageView)
        var textSeen: TextView? = view.findViewById(R.id.textSeen)
        var rightImageView: ImageView? = view.findViewById(R.id.rightImageView)
    }

    override fun getItemViewType(position: Int): Int {


        return if (chatList[position].getSender().equals(firebaseUser!!.uid)) {
            1
        } else {
            0
        }
    }
    private fun deleteSentMessage(position: Int,holder:ChatAdapter.ViewHolder){
        val ref= FirebaseDatabase.getInstance().reference.child("Chats")
            .child(chatList.get(position).getMessageId()!!)
            .removeValue()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(holder.itemView.context,"deleted", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(holder.itemView.context,"failed,to delete", Toast.LENGTH_SHORT).show()
                }
            }
    }
}