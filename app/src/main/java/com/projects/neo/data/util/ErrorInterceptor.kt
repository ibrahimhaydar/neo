package com.projects.neo.data.util

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

class ErrorInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()

        try{
        val response: Response = chain.proceed(request)
        when (response.code) {
            401 -> {
                throw IOException("Unauthorized !!")
            }
            408 -> {
                throw IOException("Unauthorized !!")
            }
        }
        return response
        }catch (e:Exception){
            throw SocketTimeoutException()
        }
    }
}