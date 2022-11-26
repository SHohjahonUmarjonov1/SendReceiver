package com.example.smsreceiver.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.widget.Toast
import java.io.File

class SmsReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, intent?.action, Toast.LENGTH_SHORT).show()
        val file=File(context?.filesDir,"message.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        file.writeText(Telephony.Sms.Intents.getMessagesFromIntent(intent).first().messageBody)
    }
}