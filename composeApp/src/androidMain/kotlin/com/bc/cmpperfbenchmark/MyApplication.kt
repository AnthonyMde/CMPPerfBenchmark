package com.bc.cmpperfbenchmark

import android.app.Application
import jp.wasabeef.takt.Takt

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Takt.stock(this)
    }
}