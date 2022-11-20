package com.projects.neo.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.projects.neo.data.model.ResponseStrings
import com.projects.neo.data.model.Strings
import com.projects.neo.data.util.AppHelper
import com.projects.neo.data.util.FileStorage
import com.projects.neo.data.util.extensions.*
import com.projects.neo.databinding.ActivitySplashBinding
import com.projects.neo.presentation.App
import com.projects.neo.presentation.BaseCompactActivity
import com.projects.neo.presentation.login.ActivityLogin
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

@AndroidEntryPoint
class ActivitySplash : BaseCompactActivity() {
    @Inject
    lateinit var factory: SplashViewModelFactory
    private lateinit var binding: ActivitySplashBinding
    private lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        splashViewModel = ViewModelProvider(this, factory)[SplashViewModel::class.java]
        getStrings()

    }

    private fun getStrings(){
        binding.loader.loading.show()
        val responseLiveData = splashViewModel.getStrings()
        responseLiveData.observe(this, Observer {
            binding.loader.loading.hide()
            AppHelper.sessionRepo.localize = Gson().toJson(it!!.data!!.strings)
            startActivity(Intent(this@ActivitySplash,ActivityLogin::class.java))
            finish()
        })
    }
}