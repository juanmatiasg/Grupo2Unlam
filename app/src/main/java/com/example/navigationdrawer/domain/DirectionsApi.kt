package com.example.navigationdrawer.domain

import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.model.DirectionsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionsApi {

    @GET("directions/json")
    suspend fun getDirections(
        @Query("destination") destination: String,
        @Query("origin") origin: String,
        @Query("key") apiKey: String = "AIzaSyAHOR3pyuH4N3vuJloY93qDGqcQPmKDMG4"
    ): DirectionsDto


}
