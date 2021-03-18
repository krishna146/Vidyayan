package com.bcebhagalpur.welcomeslider

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.messaging.FirebaseMessaging


class MyFirebaseInstanceIdService : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO)
        Log.i(
            TAG,
            "onTokenRefresh completed with token: $token"
        )
    }

    companion object {
        private const val TAG = "mFirebaseIIDService"
        private const val SUBSCRIBE_TO = "userABC"
    }
}