package com.example.meepappsample.api

import com.example.meepappsample.model.ResponseModel
import retrofit2.http.GET

interface ApiService {

    @GET("v1/routers/lisboa/resources?lowerLeftLatLon=38.711046,-9.160096&upperRightLatLon=38.739429,-9.137115")
    suspend fun getCharacters(): List<ResponseModel>
}