package com.example.smsreceiver.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.io.File
import java.io.IOException

class SmsReceiver:BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "you have received an SMS message", Toast.LENGTH_SHORT).show()
        val message=intent?.getStringExtra("message")?:return
        val file= File(context?.filesDir,"message.txt")
        Log.d("SmsReceiver",message)
        if (file.exists().not()) {
                file.createNewFile()
        }
        file.writeText(message)
    }
}