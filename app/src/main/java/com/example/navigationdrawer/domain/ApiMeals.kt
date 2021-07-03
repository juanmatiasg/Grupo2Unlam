package com.example.navigationdrawer.domain

import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.MealsInformation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMeals {


    @GET("api/json/v1/1/search.php?s=")
    suspend fun searchListMealsBySearch(@Query("s")term:String): ListMeals


    @GET("api/json/v1/1/search.php?f=b")
    suspend fun getListMeal():ListMeals

}

