package com.example.navigationdrawer.domain

import com.example.navigationdrawer.data.model.ListMeals
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMeals {

    @GET("recipes/complexSearch?query=&apiKey=9305b9a0df0b4abf8ef1440b2cb2ca2c")
    suspend fun searchListMealsBySearch(@Query("query")term:String): ListMeals
}