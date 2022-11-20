package com.projects.neo.data.repository.home

import com.projects.neo.data.model.ResponsePortfolios
import com.projects.neo.data.model.ResponseStrings
import com.projects.neo.data.repository.home.dataSource.HomeRemoteDataSource
import com.projects.neo.data.repository.splash.dataSource.SplashRemoteDataSource
import com.projects.neo.data.util.Resource
import com.projects.neo.domain.repository.HomeRepository
import com.projects.neo.domain.repository.SplashRepository
import retrofit2.Response

class HomeRepositoryImpl(private val homeRemoteDataSource: HomeRemoteDataSource):HomeRepository{

    private fun responseToPortfolio(response: Response<ResponsePortfolios>):Resource<ResponsePortfolios>{
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

    override suspend fun getPortfolios(): Resource<ResponsePortfolios>? {
        return responseToPortfolio(homeRemoteDataSource.getPortfolios())
    }


}