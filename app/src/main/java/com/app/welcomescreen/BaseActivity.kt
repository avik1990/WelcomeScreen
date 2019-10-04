package com.app.welcomescreen

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.view.View

abstract class BaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        StrictMode.allowThreadDiskReads()
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .permitDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .permitDiskWrites()
                .build()
        )
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build()
        )
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val policy = StrictMode.VmPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build()
        StrictMode.setVmPolicy(policy)
        super.onCreate(savedInstanceState)

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        setContentView(getLayout())
        InitResources()
        InitListner()
        InitPermission()

    }

    protected abstract fun InitListner()

    protected abstract fun InitResources()

    protected abstract fun InitPermission()

    protected abstract fun getLayout(): Int

}