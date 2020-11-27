package com.example.meepappsample.repository

import com.example.meepappsample.api.ApiService
import com.example.meepappsample.model.ResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository  @Inject constructor(private val apiService: ApiService) {

    suspend fun getLocations() : List<ResponseModel> {
        return apiService.getCharacters()
    }
}