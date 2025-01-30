package com.example.practiceonroom

import android.app.Application
import android.content.Context

class MainApplication : Application() {
    init {
        application = this
    }

    companion object {
        private lateinit var application: MainApplication
        fun getApplicationContext(): Context = application
    }

}