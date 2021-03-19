package com.bcebhagalpur.welcomeslider.student.dashboard.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bcebhagalpur.welcomeslider.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class TestActivity : AppCompatActivity() {

    private lateinit var txtSubject: TextView
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        txtSubject = findViewById(R.id.txtSubject)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)

        val studentSubject = intent.getStringExtra("studentSubject")
        val studentClass = intent.getStringExtra("studentClass")
        txtSubject.text = studentSubject

        val studentTest = FirebaseDatabase.getInstance().reference.child("Test")
        val studentTest1 = studentTest.child(studentClass!!)
        val studentTest2 = studentTest1.child(studentSubject!!)
        studentTest2.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val test1 = snapshot.child("Test-1").value.toString()
                    val test2 = snapshot.child("Test-2").value.toString()
                    val test3 = snapshot.child("Test-3").value.toString()
                    val test4 = snapshot.child("Test-4").value.toString()
                    val test5 = snapshot.child("Test-5").value.toString()
                    val tests = arrayOf(test1, test2, test3, test4, test5)
                    val btns = arrayOf(btn1, btn2, btn3, btn4, btn4, btn5)
//                    for (i in btns) {
//                        i.setOnClickListener {
//                            for (i1 in tests){
//                                val browserIntent =
//                                    Intent(Intent.ACTION_VIEW, Uri.parse(i1))
//                                startActivity(browserIntent)
//                            }
//
//                        }
//                    }
                    btn1.setOnClickListener {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(test1))
                        startActivity(browserIntent)
                    }
                    btn2.setOnClickListener {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(test2))
                        startActivity(browserIntent)
                    }
                    btn3.setOnClickListener {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(test3))
                        startActivity(browserIntent)
                    }
                    btn4.setOnClickListener {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(test4))
                        startActivity(browserIntent)
                    }
                    btn5.setOnClickListener {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(test5))
                        startActivity(browserIntent)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TestActivity, "some error!!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
