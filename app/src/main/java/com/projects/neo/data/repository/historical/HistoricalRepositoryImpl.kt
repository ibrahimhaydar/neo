package com.projects.neo.data.repository.historical

import com.projects.neo.data.model.ResponseHistorical
import com.projects.neo.data.repository.historical.dataSource.HistoricalRemoteDataSource
import com.projects.neo.data.util.Resource
import com.projects.neo.domain.repository.HistoricalRepository
import retrofit2.Response

class HistoricalRepositoryImpl(private val historicalRemoteDataSource: HistoricalRemoteDataSource):HistoricalRepository{

    private fun responseToHistorical(response: Response<ResponseHistorical>):Resource<ResponseHistorical>{
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

    override suspend fun getHistorical(): Resource<ResponseHistorical> {
        return responseToHistorical(historicalRemoteDataSource.getHistorical())
    }


}