package com.bcebhagalpur.welcomeslider.activity

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.adapter.ChatAdapter
import com.bcebhagalpur.welcomeslider.model.Chat
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_vidyayan_chating.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VidyayanChatingActivity : AppCompatActivity() {
    var userIdVisit:String?=""
    // var senderProfile:String?=""
    var firebaseUser: FirebaseUser?=null
    var chatsAdapter: ChatAdapter?=null
    var chatList:List<Chat>?=null
    lateinit var messageRecyclerView: RecyclerView
    var reference: DatabaseReference?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vidyayan_chating)
        userIdVisit="kFBxgCdr31f5X9nZnHqoNGBA24m1"
        firebaseUser=FirebaseAuth.getInstance().currentUser
        messageRecyclerView=findViewById(R.id.messageRecyclerView)
        messageRecyclerView.setHasFixedSize(true)
        var linearLayoutManager= LinearLayoutManager(applicationContext)
        linearLayoutManager.stackFromEnd=true
        messageRecyclerView.layoutManager=linearLayoutManager
        reference= FirebaseDatabase.getInstance().reference.child("TEACHERS").child(userIdVisit!!)
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
             //   val user: Users?=p0.getValue(Users::class.java)
                txtMessageVidyayanName.text="Vidyayan"
               // Picasso.get().load(user.getProfile()).into(imgMessageProfileImage)

                retrieveMessage(firebaseUser!!.uid,"https://firebasestorage.googleapis.com/v0/b/welcomeslider-f1285.appspot.com/o/james-thompson-seFNNqB3w8k-unsplash.jpg?alt=media&token=ae2f475b-68ca-4e5c-a0e3-42a524440c17")
            }

        })


        imgMessageSend.setOnClickListener {

            val message=et_msg_vidyayan.text.toString()
            if(message==""){
                Toast.makeText(this,"Please Write A message....", Toast.LENGTH_LONG).show()

            }else{
                sendMessageToUser(firebaseUser!!.uid,message)
            }
            et_msg_vidyayan.setText("")
        }
        imgMessageAttachImage.setOnClickListener {

            val intent= Intent()
            intent.type="image/*"
            intent.action= Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"pick image"),438)
        }
        seenMessage(userIdVisit!!)
    }


    private fun sendMessageToUser(senderId: String, message: String) {
        val reference= FirebaseDatabase.getInstance().reference
        val messageKey=reference.push().key

        val messageHashMap=HashMap<String,Any?>()
        messageHashMap["sender"]=senderId
        messageHashMap["message"]=message
      //  messageHashMap["receiver"]=receiverId
        messageHashMap["isseen"]=false
        messageHashMap["url"]=""
        messageHashMap["messageId"]=messageKey
        reference.child("Chats").child(messageKey!!).setValue(messageHashMap).addOnCompleteListener {
                task ->
            if (task.isSuccessful){
                val chatListReference=
                    FirebaseDatabase.getInstance().reference.child("newChatList").child(firebaseUser!!.uid)

                chatListReference.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {


                    }

                })




            }

        }
        //implement the push notifications using fcm

        // chatListReference.child("id").setValue(firebaseUser!!.uid)

        val userReference= FirebaseDatabase.getInstance().reference.child("TEACHERS").child(firebaseUser!!.uid)
        userReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {


            }

            override fun onDataChange(p0: DataSnapshot) {

            }

        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==438 && resultCode== RESULT_OK && data!=null && data!!.data!=null){
            val progressBar= ProgressDialog(this)
            progressBar.setMessage("image is uploading,please wait.... ")
            progressBar.show()

            val fileUri=data.data
            val storageReference= FirebaseStorage.getInstance().reference.child("Chat Images")
            val ref = FirebaseDatabase.getInstance().reference
            val messageId=ref.push().key
            val filePath=storageReference.child("$messageId.jpg")

            var uploadTask: StorageTask<*>
            uploadTask=filePath.putFile(fileUri!!)

            uploadTask.continueWithTask(Continuation <UploadTask.TaskSnapshot, Task<Uri>>{
                    task ->
                if (!task.isSuccessful){
                    task.exception?.let{
                        throw it
                    }
                }
                return@Continuation filePath.downloadUrl
            }).addOnCompleteListener {
                    task ->
                if (task.isSuccessful){
                    val downloadUrl=task.result
                    val url=downloadUrl.toString()

                    val messageHashMap=HashMap<String,Any?>()
                    messageHashMap["sender"]=firebaseUser!!.uid
                    messageHashMap["message"]="sent you an image."
                  //  messageHashMap["receiver"]=userIdVisit
                    messageHashMap["isseen"]=false
                    messageHashMap["url"]=url
                    messageHashMap["messageId"]=messageId

                    ref.child("Chats").child(messageId!!).setValue(messageHashMap).addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            progressBar.dismiss()
                            //implement the push notifications using fcm
                            val reference= FirebaseDatabase.getInstance().reference.child("users").child(firebaseUser!!.uid)
                            reference.addValueEventListener(object : ValueEventListener {
                                override fun onCancelled(p0: DatabaseError) {


                                }

                                override fun onDataChange(p0: DataSnapshot) {


                                }

                            })

                        }
                    }

                }
            }
        }
    }
    private fun retrieveMessage(senderId: String,imageUrl:String?) {
        chatList=ArrayList()
        val reference= FirebaseDatabase.getInstance().reference.child("Chats")

        reference.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                (chatList as ArrayList<Chat>).clear()
                for (snapshot in p0.children){
                    val chat=snapshot.getValue(Chat::class.java)
                  //  if (chat!!.getReceiver().equals(senderId)&& chat!!.getSender().equals(receiverId)||chat!!.getReceiver().equals(receiverId)&&chat!!.getSender().equals(senderId)){
                        (chatList as ArrayList<Chat>).add(chat!!)
                  //  }
                    chatsAdapter= ChatAdapter(this@VidyayanChatingActivity,chatList!!,"https://firebasestorage.googleapis.com/v0/b/welcomeslider-f1285.appspot.com/o/james-thompson-seFNNqB3w8k-unsplash.jpg?alt=media&token=ae2f475b-68ca-4e5c-a0e3-42a524440c17")
                    messageRecyclerView.adapter=chatsAdapter
                }
            }

        })

    }

    var seenListner: ValueEventListener?=null
    private fun seenMessage(userId:String){
        val reference= FirebaseDatabase.getInstance().reference.child("Chats")
        seenListner=reference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for (dataSnapshot in p0.children){
                    val chat =dataSnapshot.getValue(Chat::class.java)
                    if (chat!!.getReceiver().equals(firebaseUser!!.uid)&& chat!!.getSender().equals(userId)){
                        val hashMap=HashMap<String,Any>()
                        hashMap["isseen"]=true
                        dataSnapshot.ref.updateChildren(hashMap)
                    }
                }
            }

        })
    }

    override fun onPause() {
        super.onPause()

        reference!!.removeEventListener(seenListner!!)
    }

}
