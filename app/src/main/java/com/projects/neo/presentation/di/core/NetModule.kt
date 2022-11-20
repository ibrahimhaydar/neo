package com.projects.neo.presentation.di.core

import com.projects.neo.BuildConfig
import com.projects.neo.data.api.ApiService
import com.projects.neo.data.util.ErrorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl(BuildConfig.BASE_URL)
             .client(requestHeader)
             .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    private val requestHeader: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor{ it.proceed(it.request().newBuilder()
                // .addHeader("Authorization", MyApplication.userLoginInfo.token.toString())
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build())}
            .addInterceptor(ErrorInterceptor())
            .connectTimeout(1, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(1, TimeUnit.MINUTES) // write timeout
            .readTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)

            .build()



}













