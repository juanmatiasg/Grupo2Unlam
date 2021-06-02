package com.example.navigationdrawer.domain

import com.example.navigationdrawer.data.model.ListMeals
import retrofit2.http.GET

interface ApiMeals {

    @GET("api/json/v1/1/search.php?f=b")
    suspend fun getListMeals(): ListMeals
}