package com.example.smsreceiver.activity

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.smsreceiver.R
import com.example.smsreceiver.broadcasts.SmsReceiver
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
        registerReceiver()
        val messageText=findViewById<TextView>(R.id.message)
        val btn_send=findViewById<Button>(R.id.send)
        val btn_read=findViewById<Button>(R.id.read)
        val send_message=findViewById<EditText>(R.id.sendMessage)
        btn_send.setOnClickListener {
            val message=send_message.text.trim().toString()
            if (message != "") {
                val intent=Intent("com.example.smsReceiver")
                intent.putExtra("message",send_message.text.trim().toString())
                sendBroadcast(intent)
                Toast.makeText(this, "Success message", Toast.LENGTH_SHORT).show()
            }
        }
        btn_read.setOnClickListener {
            val file= File(filesDir,"message.txt")
            if (file.exists()) {
                val message=file.readText()
                messageText.text=message
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
    private fun registerReceiver() {
        val receiver=SmsReceiver()
        val intentFilter=IntentFilter("com.example.smsReceiver")
        registerReceiver(receiver,intentFilter)
    }
}