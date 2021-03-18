@file:Suppress("DEPRECATION")

package com.bcebhagalpur.welcomeslider.student.dashboard.adapter

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.TeacherDemoVideoActivity
import com.bcebhagalpur.welcomeslider.student.dashboard.activity.TeacherDetailActivity
import com.bcebhagalpur.welcomeslider.student.dashboard.model.ExploreTeacherListModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
class ExploreTeacherListAdapter(
    private val context: Context,
    private val itemList: ArrayList<ExploreTeacherListModel>
) : RecyclerView.Adapter<ExploreTeacherListAdapter.StatusViewHolder>() {


    private val fcmApi = "https://fcm.googleapis.com/fcm/send"
    private val serverKey = "key=" + "\tAAAARk682pg:APA91bEuj5BTYpNG8joKW8APOqB3I2gEVyHom46zGgJxpOd8tAvlM0wAIc5fVJ97gZCdS8tsxd35NF0m6HC6XyFIdmkTRZUmfd3KNcU0Gr6tYD1Ei9ggQPnUTgIHb7-h6coe9ETxMTCw"
    private val contentType = "application/json"
    val tag = "NOTIFICATION TAG"

    var notificationTitle: String? = null
    var notificationMessage: String? = null
    var topic: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.explore_student_teacher_list, parent, false)
        return StatusViewHolder(view)

    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {

//        val notificationId=4
//        val channelId="NOTIFICATION_CHANNEL_ID"
        val subject = itemList[position]
       Picasso.get().load(subject.teacherImage).error(R.drawable.logo_transparant).into(holder.teacherImage)
     holder.teacherName.text=subject.teacherName
        holder.teacherQualification.text=subject.qualification
        if (subject.subject=="Select Subject"){
            holder.teacherSubject.text=context.getString(R.string.all_subject)
        }else{
            holder.teacherSubject.text=subject.subject
        }
        holder.teacherTiming.text=subject.timing
        holder.teacherGrade.text=subject.teacherClass
        holder.rating.numStars=5
        holder.linearLayout.setOnClickListener {
            val intent=Intent(context, TeacherDetailActivity::class.java)
            intent.putExtra("teacherClass", subject.teacherClass)
            intent.putExtra("college", subject.college)
            intent.putExtra("language", subject.language)
            intent.putExtra("teacherImage", subject.teacherImage)
            intent.putExtra("mobileNumber", subject.mobileNumber)
            intent.putExtra("mode", subject.mode)
            intent.putExtra("price", subject.price)
            intent.putExtra("qualification", subject.qualification)
            intent.putExtra("status", subject.status)
            intent.putExtra("subject", subject.subject)
            intent.putExtra("teacherAddress", subject.teacherAddress)
            intent.putExtra("teacherCity", subject.teacherCity)
            intent.putExtra("teacherDob", subject.teacherDob)
            intent.putExtra("teacherEmail", subject.teacherEmail)
            intent.putExtra("teacherGender", subject.teacherGender)
            intent.putExtra("teacherName", subject.teacherName)
            intent.putExtra("timing", subject.timing)
            intent.putExtra("userId", subject.userId)
           context.startActivity(intent)
        }
        holder.btnDemoVideo.setOnClickListener {
            if (subject.teacherDemoVideo!=null){
                val intent=Intent(context, TeacherDemoVideoActivity::class.java)
                intent.putExtra("videoUrl", subject.teacherDemoVideo)
                context.startActivity(intent)
            }else{
                Snackbar.make(
                    holder.linearLayout,
                    "Teacher not added demo video yet...",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        holder.btnSendRequest.setOnClickListener {
            val progressDialog = ProgressDialog(context)
            progressDialog.setTitle("VIDYAYAN")
            progressDialog.setMessage("We are checking your status")
            progressDialog.show()
            val userId= FirebaseAuth.getInstance().currentUser!!.uid
            val userNumber= FirebaseAuth.getInstance().currentUser!!.phoneNumber
                        val request = FirebaseDatabase.getInstance().reference.child("Request")
                        val anotherChild=request.child(subject.userId!!).child(userId)
                        anotherChild.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                progressDialog.hide()
                                if (!snapshot.exists()) {
                                    val student =
                                        FirebaseDatabase.getInstance().reference.child("STUDENT")
                                    val anotherChildStudent = student.child(userId)
                                    anotherChildStudent.addListenerForSingleValueEvent(object :
                                        ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            if (snapshot.exists()) {
                                                val studentCity =
                                                    snapshot.child("studentCity").value.toString()
                                                val studentClass =
                                                    snapshot.child("studentClass").value.toString()
                                                val studentName =
                                                    snapshot.child("studentName").value.toString()
                                                request.addListenerForSingleValueEvent(object :
                                                    ValueEventListener {
                                                    override fun onDataChange(snapshot: DataSnapshot) {
                                                        val anotherChild2 =
                                                            request.child(subject.userId)
                                                        val anotherChild1 =
                                                            anotherChild2.child(userId)
                                                        anotherChild1.child("studentCity").setValue(
                                                            studentCity
                                                        )
                                                        anotherChild1.child("studentClass")
                                                            .setValue(
                                                                studentClass
                                                            )
                                                        anotherChild1.child("studentName").setValue(
                                                            studentName
                                                        )
                                                        anotherChild1.child("studentNumber")
                                                            .setValue(
                                                                userNumber
                                                            )
                                                        anotherChild1.child("studentId").setValue(
                                                            userId
                                                        )
                                                        anotherChild1.child("teacherName").setValue(
                                                            subject.teacherName
                                                        )
                                                        anotherChild1.child("teacherNumber")
                                                            .setValue(
                                                                subject.mobileNumber
                                                            )
                                                        anotherChild1.child("teacherId").setValue(
                                                            subject.userId
                                                        )
                                                        anotherChild1.child("teacherCity").setValue(
                                                            subject.teacherCity
                                                        )

//                                                        val builder = NotificationCompat.Builder(
//                                                            context,
//                                                            channelId
//                                                        )
//                                                        builder.setSmallIcon(R.drawable.logo_transparant)
//                                                            .setContentTitle("Vidyayan Edventure")
//                                                            .setAutoCancel(true)
//                                                            .setStyle(
//                                                                NotificationCompat.BigTextStyle()
//                                                                    .bigText(
//                                                                        "$studentName send you request"
//                                                                    )
//                                                            )
//                                                            .setDefaults(NotificationCompat.DEFAULT_ALL)
//
//                                                        val intent = Intent()
//                                                        intent.putExtra(
//                                                            "NotificationKey",
//                                                            "Notification"
//                                                        )
//                                                        val notificationIntent = Intent(
//                                                            context,
//                                                            HomeActivity::class.java
//                                                        )
//                                                        val pendingIntent =
//                                                            PendingIntent.getActivity(
//                                                                context,
//                                                                0,
//                                                                notificationIntent,
//                                                                PendingIntent.FLAG_UPDATE_CURRENT
//                                                            )
//
//                                                        builder.setContentIntent(pendingIntent)
//
//                                                        val manager = context.getSystemService(
//                                                            Context.NOTIFICATION_SERVICE
//                                                        ) as NotificationManager
//
//                                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                                                            val channel = NotificationChannel(
//                                                                channelId, "Title",
//                                                                NotificationManager.IMPORTANCE_DEFAULT
//                                                            )
//                                                            manager.createNotificationChannel(
//                                                                channel
//                                                            )
//                                                            builder.setChannelId(channelId)
//                                                        }
//                                                        manager.notify(
//                                                            notificationId,
//                                                            builder.build()
//                                                        )
//                                                        Toast.makeText(
//                                                            context,
//                                                            "Notification",
//                                                            Toast.LENGTH_SHORT
//                                                        ).show()
                                                        Snackbar.make(
                                                            holder.linearLayout,
                                                            "Request sent successfully...",
                                                            Snackbar.LENGTH_LONG
                                                        ).show()


                                                        topic =
                                                            "/topics/userABC" //topic must match with what the receiver subscribed to

                                                        notificationTitle = "Request"
                                                        notificationMessage =
                                                            "$studentName sent you request"

                                                        val notification = JSONObject()
                                                        val notificationBody = JSONObject()
                                                        try {
                                                            notificationBody.put(
                                                                "title",
                                                                notificationTitle
                                                            )
                                                            notificationBody.put(
                                                                "message",
                                                                notificationMessage
                                                            )
                                                            notification.put("to", topic)
                                                            notification.put(
                                                                "data",
                                                                notificationBody
                                                            )
                                                        } catch (e: JSONException) {
                                                            Log.e(tag, "onCreate: " + e.message)
                                                        }
                                                        sendNotification(notification)
                                                    }

                                                    override fun onCancelled(error: DatabaseError) {

                                                    }

                                                })
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {

                                        }

                                    })
                                } else {
                                    Snackbar.make(
                                        holder.linearLayout,
                                        "Request already sent...",
                                        Snackbar.LENGTH_LONG
                                    ).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {

                            }
                        })
        }
    }
        class StatusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val teacherImage: ImageView = view.findViewById(R.id.imgTeacher)
        val teacherName: TextView= view.findViewById(R.id.txtTeacherName)
        val teacherGrade:TextView=view.findViewById(R.id.txtTeacherGrade)
        val teacherTiming:TextView=view.findViewById(R.id.txtTeacherTime)
        val teacherQualification: TextView = view.findViewById(R.id.txtQualification)
        val teacherSubject: TextView= view.findViewById(R.id.txtTeacherSubject)
        val btnDemoVideo: Button= view.findViewById(R.id.teacherDemoVideo)
        val btnSendRequest: Button= view.findViewById(R.id.btnSendRequest)
        val rating: RatingBar = view.findViewById(R.id.rating)
        val linearLayout: LinearLayout = view.findViewById(R.id.llt)
    }

    private fun sendNotification(notification: JSONObject) {
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(fcmApi, notification,
            Response.Listener { response ->
                Log.i(tag, "onResponse: $response")

            },
            Response.ErrorListener {
                Toast.makeText(context, "Request error", Toast.LENGTH_LONG).show()
                Log.i(tag, "onErrorResponse: Didn't work")
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Authorization"] = serverKey
                params["Content-Type"] = contentType
                return params
            }
        }
        MySingleton.getInstance(context)?.addToRequestQueue(jsonObjectRequest)
    }

    class MySingleton private constructor(private val ctx: Context) {
        private var requestQueue: RequestQueue?
        private fun getRequestQueue(): RequestQueue? {
            if (requestQueue == null) {
                // getApplicationContext() is key, it keeps you from leaking the
                // Activity or BroadcastReceiver if someone passes one in.
                requestQueue = Volley.newRequestQueue(ctx.applicationContext)
            }
            return requestQueue
        }

        fun <T> addToRequestQueue(req: Request<T>?) {
            getRequestQueue()!!.add(req)
        }

        companion object {
            @SuppressLint("StaticFieldLeak")
            private var instance: MySingleton? = null
            @Synchronized
            fun getInstance(context: Context): MySingleton? {
                if (instance == null) {
                    instance = MySingleton(context)
                }
                return instance
            }
        }

        init {
            requestQueue = getRequestQueue()
        }
    }


}
