package com.projects.neo.presentation.login.fragments

import com.projects.neo.data.util.Resource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.projects.neo.data.util.enums.LoginErrors

class LoginViewModel(): ViewModel() {

    fun login(username:String?,password:String?) = liveData {
        if(username == "admin" && password == "admin"){
            emit(Resource.Success(""))
        }else{
            when {
                username.isNullOrEmpty() && password.isNullOrEmpty()-> emit(Resource.Error(LoginErrors.EMPTY_USERNAME_PASSWORD.id))
                username.isNullOrEmpty() -> emit(Resource.Error(LoginErrors.EMPTY_EMAIL.id))
                password.isNullOrEmpty() -> emit(Resource.Error(LoginErrors.EMPTY_PASSWORD.id))
                else ->   emit(Resource.Error(LoginErrors.WRONG_USERNAME_PASSWORD.id))
            }

        }
    }

}


