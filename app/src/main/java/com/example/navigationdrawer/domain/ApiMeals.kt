package com.example.navigationdrawer.domain

import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.MealsInformation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMeals {

    //https://www.themealdb.com/
    //https://www.themealdb.com/api/json/v1/1/search.php?f=a
    @GET("api/json/v1/1/search.php?s=")
    suspend fun searchListMealsBySearch(@Query("s")term:String): ListMeals

    //api/json/v1/1/search.php?f=a
    /*@GET("recipes/{id}/information?apiKey=${apiKeyJuan}")
    suspend fun getInformationMeals(@Path("id")id:String): MealsInformation

    companion object{
        const val apiKeyMariano = "9305b9a0df0b4abf8ef1440b2cb2ca2c"
        const val apiKeyJuan = "3debba4510294d7480646c8f48d7624b"
        const val apiKeyMariano2="2094db6c4f1a4ac0a9393de34ad7f53a"
    }
    */
    @GET("api/json/v1/1/search.php?f=b")
    suspend fun getListMeal():ListMeals

}

