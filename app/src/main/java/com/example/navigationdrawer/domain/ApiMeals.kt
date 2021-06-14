package com.example.navigationdrawer.domain

import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.MealsInformation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMeals {

    @GET("recipes/complexSearch?query=&apiKey=${apiKeyJuan}")
    suspend fun searchListMealsBySearch(@Query("query")term:String): ListMeals


    @GET("recipes/{id}/information?apiKey=${apiKeyJuan}")
    suspend fun getInformationMeals(@Path("id")id:String): MealsInformation

    companion object{
        const val apiKeyMariano = "9305b9a0df0b4abf8ef1440b2cb2ca2c"
        const val apiKeyJuan = "3debba4510294d7480646c8f48d7624b"
    }

}

