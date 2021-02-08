package com.bcebhagalpur.welcomeslider.model

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import kotlin.concurrent.timerTask


//@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseInstanceIDService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val refreshToken = FirebaseInstanceId.getInstance().token
        if (firebaseUser != null) {
            updateToken(refreshToken)
        }
    }


    private fun updateToken(refreshToken: String?) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = FirebaseDatabase.getInstance().reference.child("Tokens")
        val tokenHashMap = HashMap<String, Any>()
        tokenHashMap["uid"] = userId
        tokenHashMap["token"] = refreshToken!!
        ref.setValue(tokenHashMap).addOnCompleteListener { Task ->
            if (Task.isSuccessful) {
                Log.d(TAG, "Refreshed token: $refreshToken ");
            } else {
                Log.d(TAG, "Refreshed token: ");
            }

        }
    }
}
/*  fun onTokenRefresh() {
     // Get updated InstanceID token.
     val firebaseUser=FirebaseAuth.getInstance().currentUser
     val refreshToken= FirebaseInstanceId.getInstance().token

     Log.d(TAG, "Refreshed token: $refreshToken")
     Log.d(TAG, "Refreshed token: ")

     // If you want to send messages to this application instance or
     // manage this apps subscriptions on the server side, send the
     // Instance ID token to your app server.
     if (firebaseUser!=null){
         updateToken(refreshToken)
     }

 }


 private fun updateToken(refreshToken: String?) {
     val firebaseUser = FirebaseAuth.getInstance().currentUser
     val userId = FirebaseAuth.getInstance().currentUser!!.uid
     val ref = FirebaseDatabase.getInstance().reference.child("Tokens")
     val tokenHashMap = HashMap<String, Any>()
     tokenHashMap["uid"] = userId
     tokenHashMap["token"] = refreshToken!!
     ref.setValue(tokenHashMap).addOnCompleteListener {
         Task->
         if (Task.isSuccessful)
         {
             Toast.makeText(this,"token saved",Toast.LENGTH_SHORT).show()
         }else
         {
             Toast.makeText(this,"token saved",Toast.LENGTH_SHORT).show()
         }

     }
 }*/

