package com.szhua.foryou

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ForYouApp : Application(){

    companion object{
        private lateinit var app :ForYouApp
        fun  getInstance():ForYouApp{
            return  app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        Logger.addLogAdapter(AndroidLogAdapter())
    }

}



