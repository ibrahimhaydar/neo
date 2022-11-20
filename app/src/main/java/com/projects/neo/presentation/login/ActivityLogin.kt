package com.projects.neo.presentation.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.projects.neo.R
import com.projects.neo.data.util.extensions.hide
import com.projects.neo.data.util.extensions.show
import com.projects.neo.databinding.ActivityLoginBinding
import com.projects.neo.databinding.ActivitySplashBinding
import com.projects.neo.presentation.BaseCompactActivity
import com.projects.neo.presentation.login.fragments.LoginViewModel
import com.projects.neo.presentation.login.fragments.LoginViewModelFactory
import com.projects.neo.presentation.splash.SplashViewModel
import com.projects.neo.presentation.splash.SplashViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ActivityLogin : BaseCompactActivity() {
    @Inject
    lateinit var factory: LoginViewModelFactory
    private lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
     }


}