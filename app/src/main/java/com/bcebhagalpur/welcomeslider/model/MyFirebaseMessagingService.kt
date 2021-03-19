package com.bcebhagalpur.welcomeslider.model

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {
    lateinit var broadcaster: LocalBroadcastManager;


    override fun onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
    }
    override fun onMessageReceived(p0: RemoteMessage) {

       // try {


            val notification_data=p0.data
          //  val value1=notification_data.get("key_1")
          //  val value2=notification_data.get("key_2")
            val title=notification_data.get("title")
            val msg=notification_data.get("body")
        val intent = Intent("MyData")
        intent.putExtra("title", title)
        intent.putExtra("msg", msg)

        broadcaster.sendBroadcast(intent)
          val  mDatabase = FirebaseDatabase.getInstance()
           val mDatabaseReference = mDatabase.reference.child("Notification")
           val mAuth= FirebaseAuth.getInstance()
            val userId= mAuth.currentUser!!.uid

            val currentUserDb = mDatabaseReference.child(userId!!)

            currentUserDb.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val anotherChild = currentUserDb.child(userId)
                    anotherChild.child("userId").setValue(userId)
                //    anotherChild.child("value1").setValue(value1!!.toString())
                 //   anotherChild.child("value2").setValue(value2!!.toString())
                    anotherChild.child("title").setValue(title!!.toString())
                    anotherChild.child("msg").setValue(msg!!.toString())

                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
            if (p0.data.size>0){
                Log.d(TAG, "Message data payload: " + p0.data)
            }
            if (p0.getNotification() != null) {
                Log.d(TAG, "Message Notification Body: " + p0.getNotification()!!.getBody());
            }
      //  }catch (e:Exception){
        //    Log.d("Error Line Number",Log.getStackTraceString(e))
        //}

    }
}