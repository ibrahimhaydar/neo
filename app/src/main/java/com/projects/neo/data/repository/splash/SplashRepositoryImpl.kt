package com.projects.neo.data.repository.splash

import com.projects.neo.data.model.ResponseStrings
import com.projects.neo.data.repository.splash.dataSource.SplashRemoteDataSource
import com.projects.neo.data.util.Resource
import com.projects.neo.domain.repository.SplashRepository
import retrofit2.Response

class SplashRepositoryImpl(private val splashRemoteDataSource: SplashRemoteDataSource):SplashRepository{

    private fun responseToStrings(response: Response<ResponseStrings>):Resource<ResponseStrings>{
        try{
            if(response.isSuccessful){
                response.body()?.let {result->
                    return Resource.Success(result)
                }
            }
            return Resource.Error(response.message())

        }catch (e:Exception){
            return Resource.Error("Error")
        }

    }

    override suspend fun getStrings(): Resource<ResponseStrings> {
        return responseToStrings(splashRemoteDataSource.getStrings())
    }


}