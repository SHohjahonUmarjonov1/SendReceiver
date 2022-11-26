package com.example.bootcompletedreceiver

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private var callPermission=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    private fun initViews() {
        requestPermissions()
    }

    private val callLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        callPermission=it?:callPermission
    }
    private fun requestPermissions() {
        if (!callPermission) {
            callLauncher.launch(Manifest.permission.READ_PHONE_STATE)
        }
    }
}