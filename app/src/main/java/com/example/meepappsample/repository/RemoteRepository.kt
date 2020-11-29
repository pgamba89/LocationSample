package com.example.meepappsample.repository

import com.example.meepappsample.api.ApiService
import com.example.meepappsample.model.ResponseModel
import com.example.meepappsample.utils.Constants.Companion.lowerLeftLatLon
import com.example.meepappsample.utils.Constants.Companion.upperRightLatLon
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository  @Inject constructor(private val apiService: ApiService) {

    suspend fun getLocations() : List<ResponseModel> {
        return apiService.getCharacters(lowerLeftLatLon, upperRightLatLon )
    }
}