package com.bcebhagalpur.welcomeslider.activity

import android.content.Context
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.xml.transform.Source
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

sealed class FirebaseAuthUserState
    data class UserSignedIn(val user: FirebaseUser) : FirebaseAuthUserState()
object UserSignedOut : FirebaseAuthUserState()
object UserUnknown : FirebaseAuthUserState()

//@MainThread
//fun FirebaseAuth.newFirebaseAuthStateLiveData(
//    context: CoroutineContext = EmptyCoroutineContext
//): LiveData<FirebaseAuthUserState> {
//    val ld = FirebaseAuthStateLiveData(this)
//    return liveData(context) {
//        emitSource(ld)
//    }
//}
//
//fun emitSource(ld: FirebaseAuthStateLiveData) {
//
//}
//
//fun liveData(context: CoroutineContext, function: () -> Unit): LiveData<FirebaseAuthUserState> {
//
//}

class FirebaseAuthStateLiveData(private val auth: FirebaseAuth) : LiveData<FirebaseAuthUserState>() {
    private val authStateListener = MyAuthStateListener()
    init {
        value = UserUnknown
    }
    override fun onActive() {
        auth.addAuthStateListener(authStateListener)
    }
    override fun onInactive() {
        auth.removeAuthStateListener(authStateListener)
    }
    private inner class MyAuthStateListener : FirebaseAuth.AuthStateListener {
        override fun onAuthStateChanged(auth: FirebaseAuth) {
            val user = auth.currentUser
            value = if (user != null) {
                UserSignedIn(user)
            }
            else {
                UserSignedOut
            }
        }
    }
}