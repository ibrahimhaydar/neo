package com.projects.neo.domain.repository

import com.projects.neo.data.model.ResponseStrings
import com.projects.neo.data.util.Resource


interface SplashRepository {
    suspend fun getStrings():Resource<ResponseStrings>?
}