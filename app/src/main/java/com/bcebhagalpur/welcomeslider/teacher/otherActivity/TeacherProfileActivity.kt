package com.bcebhagalpur.welcomeslider.teacher.otherActivity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import com.bcebhagalpur.welcomeslider.R
import kotlinx.android.synthetic.main.activity_teacher_profile.*
import kotlinx.android.synthetic.main.activity_teacher_registration3.*
import java.net.URI

class TeacherProfileActivity : AppCompatActivity() {

    lateinit var btnUploadKYC: Button
    lateinit var filepath: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_profile)
        btnUploadKYC = findViewById(R.id.btnuploadKYC)
        val documentItems = listOf("Aadhar Card", "Pan Card", "Driving License")
        val documentAdapter = ArrayAdapter(this, R.layout.list_item, documentItems)
        actxtKycDocuments.setAdapter(documentAdapter)

        btnUploadKYC.setOnClickListener() {
            startFileChooser()
        }
    }



    private fun startFileChooser() {
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i, "choose picture",), 111)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 111 && resultCode == Activity.RESULT_OK && data!=null){
            filepath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)
            btnUploadKYC.setText("Selected")
        }

    }


    }