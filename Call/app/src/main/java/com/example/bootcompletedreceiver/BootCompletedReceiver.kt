package com.example.bootcompletedreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootCompletedReceiver(): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Toast.makeText(context?.applicationContext, intent?.action, Toast.LENGTH_SHORT).show()
            Log.d("myapp", intent?.action.toString())
        }
    }
}