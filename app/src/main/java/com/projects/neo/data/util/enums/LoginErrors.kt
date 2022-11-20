package com.projects.neo.data.util.enums


enum class LoginErrors(val id:String) {
    EMPTY_EMAIL("1"),
    EMPTY_PASSWORD("2"),
    EMPTY_USERNAME_PASSWORD("3"),
    WRONG_USERNAME_PASSWORD("4"),
    ERROR(""),
}