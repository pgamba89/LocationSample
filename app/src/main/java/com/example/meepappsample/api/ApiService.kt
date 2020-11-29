package com.example.meepappsample.api

import com.example.meepappsample.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/routers/lisboa/resources")
    suspend fun getCharacters(
        @Query("lowerLeftLatLon") lowerLeftLatLon: String,
        @Query("upperRightLatLon") upperRightLatLon: String,
    ): List<ResponseModel>
}