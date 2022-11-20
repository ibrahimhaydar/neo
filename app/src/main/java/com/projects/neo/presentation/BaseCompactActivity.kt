package com.projects.neo.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.ViewPumpAppCompatDelegate
import com.projects.neo.data.util.AppHelper
import com.projects.neo.data.util.LocaleUtils
import dev.b3nedikt.restring.Restring
import java.util.*

open class BaseCompactActivity:AppCompatActivity() {
    private val appCompatDelegate: AppCompatDelegate by lazy {
        ViewPumpAppCompatDelegate(
            baseDelegate = super.getDelegate(),
            baseContext = this,
            wrapContext = Restring::wrapContext
        )
    }
    override fun getDelegate(): AppCompatDelegate {
        return appCompatDelegate
    }
    init {

           //LocaleUtils.updateConfig(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppHelper.setLocal(this)
        AppHelper.setLocalStrings(this, Locale(AppHelper.sessionRepo.language))
        super.onCreate(savedInstanceState)
    }


    override fun attachBaseContext(newBase: Context) {
        var attachedBase = newBase
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val config = attachedBase.resources.configuration
            config.setLocale(Locale.getDefault())
            attachedBase = attachedBase.createConfigurationContext(config)
        }
        super.attachBaseContext(attachedBase)
    }
}