package com.example.smsreceiver.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.smsreceiver2.R
import java.io.File

class MainActivity : AppCompatActivity() {
    private var smsPermission=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    private fun initViews() {
        requestPermission()
        val message=findViewById<TextView>(R.id.message)
        val readMessage=findViewById<Button>(R.id.readMessage)
        readMessage.setOnClickListener {
            val file= File(filesDir,"message.txt")
            if (file.exists()) {
                message.text=file.readText()
            } else {
                return@setOnClickListener
            }
        }
    }

    private val smsLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        smsPermission=
            Manifest.permission.RECEIVE_SMS == (PackageManager.PERMISSION_GRANTED ?: smsPermission)
    }
    private fun requestPermission() {
        if (!smsPermission) {
            smsLauncher.launch(Manifest.permission.RECEIVE_SMS)
        }
    }
}