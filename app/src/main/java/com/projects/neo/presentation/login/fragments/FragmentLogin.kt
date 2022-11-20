package com.projects.neo.presentation.login.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import com.projects.neo.R
import com.projects.neo.data.util.enums.LoginErrors
import com.projects.neo.data.util.extensions.debugOnly
import com.projects.neo.data.util.extensions.onSingleClick
import com.projects.neo.databinding.FragmentLoginBinding
import com.projects.neo.presentation.BaseFragments
import com.projects.neo.presentation.home.ActivityHome
import com.projects.neo.presentation.login.ActivityLogin


class FragmentLogin : BaseFragments() {

    private lateinit var fragmentLoginBinding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    lateinit var  shake: Animation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentLoginBinding = FragmentLoginBinding.bind(view)
        init()
    }

    private fun init(){
        shake =  AnimationUtils.loadAnimation(requireActivity(), R.anim.shake)
        loginViewModel = (activity as ActivityLogin).loginViewModel
        listeners()
        debugOnly {
            fragmentLoginBinding.etEmail.setText("admin")
            fragmentLoginBinding.etPassword.setText("admin")
        }
    }

    private fun listeners(){
        fragmentLoginBinding.btLogin.onSingleClick {
            login()
        }
    }

    private fun login(){
        val responseLiveData = loginViewModel.login(
            username = fragmentLoginBinding.etEmail.text.toString().trim(),
            password = fragmentLoginBinding.etPassword.text.toString().trim()
        )
        responseLiveData.observe(requireActivity(), Observer {
           if(it.message.isNullOrEmpty())
               goNext()
            else
               checkError(it.message)
        })
    }

    private fun checkError(error:String){
        when (error) {
            LoginErrors.EMPTY_EMAIL.id -> fragmentLoginBinding.etEmail.startAnimation(shake)
            LoginErrors.EMPTY_PASSWORD.id -> fragmentLoginBinding.etEmail.startAnimation(shake)
            else -> {
                fragmentLoginBinding.etEmail.startAnimation(shake)
                fragmentLoginBinding.etPassword.startAnimation(shake) }
        }
    }

    private fun goNext(){
        startActivity(Intent(requireActivity(),ActivityHome::class.java))
        requireActivity().finish()
    }
}


