package com.example.navigationdrawer.domain

import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.model.DirectionsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionsApi {

    @GET("directions/json")
    suspend fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        //@Query("key") apiKey: String = "AIzaSyAvyrhaNDjhBP0pAniZf1DSYz6Hu_yLLUY"
        @Query("key") apiKey: String = "${R.string.google_maps_key}"
    ): DirectionsDto
}
