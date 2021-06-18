package com.example.navigationdrawer.vo

import com.example.navigationdrawer.domain.ApiMeals
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {

    val webService by lazy{
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiMeals::class.java)
    }
}