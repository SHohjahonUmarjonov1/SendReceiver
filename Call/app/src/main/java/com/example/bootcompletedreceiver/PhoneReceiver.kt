package com.example.bootcompletedreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.widget.Toast

class PhoneReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)==TelephonyManager.EXTRA_STATE_RINGING) {
            Toast.makeText(context, "Start Call ...", Toast.LENGTH_SHORT).show()
        } else if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)==TelephonyManager.EXTRA_STATE_IDLE) {
            Toast.makeText(context, "End Call ...", Toast.LENGTH_SHORT).show()
        } else if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)==TelephonyManager.EXTRA_STATE_OFFHOOK) {
            Toast.makeText(context, "Call ...", Toast.LENGTH_SHORT).show()
        }
    }
}