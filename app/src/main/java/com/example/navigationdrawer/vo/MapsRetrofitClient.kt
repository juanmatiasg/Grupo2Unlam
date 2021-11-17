package com.example.navigationdrawer.vo

import com.example.navigationdrawer.domain.DirectionsApi
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MapsRetrofitClient {

    fun getDirectionsApi(): DirectionsApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .build()
            .create(DirectionsApi::class.java)
}