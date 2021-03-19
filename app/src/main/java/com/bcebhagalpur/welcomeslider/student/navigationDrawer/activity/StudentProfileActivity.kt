package com.bcebhagalpur.welcomeslider.student.navigationDrawer.activity

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Toast

import com.bcebhagalpur.welcomeslider.R
import com.bcebhagalpur.welcomeslider.fragment.Class11thor12thDropdownFragment
import com.bcebhagalpur.welcomeslider.fragment.Class12thor11thFragment
import com.bcebhagalpur.welcomeslider.fragment.Class13thDropdownFragment
import com.bcebhagalpur.welcomeslider.fragment.Class1to10thDropdownFragment
import com.bcebhagalpur.welcomeslider.student.dashboard.model.student
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_student_profile.*
import kotlinx.android.synthetic.main.activity_teacher_registration2.*

class StudentProfileActivity : AppCompatActivity() {
    lateinit var profile_pic:ImageView
    var userId:FirebaseUser?=null
    var userReference:DatabaseReference?=null
    var storageReference:StorageReference?=null
    val requestCODE=111
    private var imageUrl: Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)
       // val autoCompleteTextView =
         //   findViewById<AutoCompleteTextView>(R.id.AutoCompleteTextView)
        profile_pic=findViewById(R.id.upload_profile_pic)
        userId=FirebaseAuth.getInstance().currentUser
        userReference=FirebaseDatabase.getInstance().reference.child("STUDENT").child(userId!!.uid)
        storageReference=FirebaseStorage.getInstance().reference.child("student image")

       userReference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    val pic=p0.child("student pic").value.toString()
                    Picasso.get().load(pic).placeholder(R.drawable.logo_transparant).into(profile_pic)

                }

            }

        })
        profile_pic.setOnClickListener {
            pickImage()
        }
        val menuItems = listOf("13th", "12th", "11th", "10th", "9th","8th", "7th", "6th", "5th", "4th", "3rd", "2nd", "1st")
        val adapter = ArrayAdapter(this, R.layout.list_item, menuItems)
        autoCompleteTextView.setOnClickListener {
            val gender = arrayOf("13th", "12th", "11th", "10th", "9th","8th", "7th", "6th", "5th", "4th", "3rd", "2nd", "1st")
            val mAlertDialogBuilder = AlertDialog.Builder(this)
            mAlertDialogBuilder.setTitle("Select Class")
            mAlertDialogBuilder.setItems(gender) { _, which ->
                when (which) {
                    which -> {
                        et_class.setText(gender[which])
                        et_class.setTypeface(null, Typeface.BOLD)
                    }
                }
            }
            val mAlertDialog = mAlertDialogBuilder.create()
            mAlertDialog.show()
        }
       // autoCompleteTextView.setAdapter(adapter)

       // autoCompleteTextView.setText("Select Your Class", false)
       /* autoCompleteTextView.setOnItemClickListener { adapter, view, i, l ->
            var selectedItem = adapter.getItemAtPosition(i)
            if(selectedItem=="13th"){
                val transaction = supportFragmentManager.beginTransaction().replace(R.id.frame1, Class13thDropdownFragment())
                transaction.commit()
            }
            if(selectedItem=="12th" || selectedItem== "11th"){
                val transaction = supportFragmentManager.beginTransaction().replace(R.id.frame1, Class11thor12thDropdownFragment())
                transaction.commit()
            }
            if(selectedItem=="10th"||selectedItem=="9th"||selectedItem=="8th"||selectedItem=="7th"||selectedItem=="6th"||selectedItem=="5th"||selectedItem=="4th"||selectedItem=="3rd"||selectedItem=="2nd"||selectedItem=="1st"){
                val transaction = supportFragmentManager.beginTransaction().replace(R.id.frame1, Class1to10thDropdownFragment())
                transaction.commit()
            }

        }*/

    }

    private fun pickImage() {
        val intent= Intent()
        intent.type="image/*"
        intent.action= Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,requestCODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==requestCODE&&resultCode== Activity.RESULT_OK&&data!!.data!=null){
            imageUrl=data.data
            Toast.makeText(this,"uploading,,,,,",Toast.LENGTH_LONG).show()
            uploadImageToDatabase()
        }
    }
    private fun uploadImageToDatabase() {
        val progressBar= ProgressDialog(this)
        progressBar.setMessage("image is uploading,please wait.....")
        progressBar.show()
        if(imageUrl!=null){
            val fileRef=storageReference!!.child(System.currentTimeMillis().toString()+".jpg")

            var uploadTask: StorageTask<*>
            uploadTask=fileRef.putFile(imageUrl!!)

            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> {
                    task ->
                if (!task.isSuccessful){
                    task.exception?.let {
                        throw it
                    }

                }
                return@Continuation fileRef.downloadUrl
            }).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val downloadUrl=task.result
                    val url=downloadUrl.toString()


                        val mapProfileImage=HashMap<String,Any>()
                        mapProfileImage["student pic"]=url
                        userReference!!.updateChildren(mapProfileImage)

                    }
                    progressBar.dismiss()
                }
            }
        }
    }

