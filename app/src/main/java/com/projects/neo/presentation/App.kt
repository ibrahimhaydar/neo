package com.projects.neo.presentation

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.projects.neo.data.model.AppLocaleLocaleProvider
import dagger.hilt.android.HiltAndroidApp
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.restring.Restring
import dev.b3nedikt.reword.RewordInterceptor
import dev.b3nedikt.viewpump.ViewPump
import java.util.*

@HiltAndroidApp
class App :Application(){

    companion object{
        internal lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
        initLocalization()
    }

     private fun initLocalization(){
        AppLocale.supportedLocales = listOf(Locale.ENGLISH, Locale("ar"))
        Restring.init(this)
        Restring.localeProvider = AppLocaleLocaleProvider
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        ViewPump.init(RewordInterceptor)
    }
}