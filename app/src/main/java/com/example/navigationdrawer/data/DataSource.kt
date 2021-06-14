package com.example.navigationdrawer.data

import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.MealsInformation
import com.example.navigationdrawer.vo.Resource
import com.example.navigationdrawer.vo.RetrofitClient

class DataSource {
    suspend fun getListMeals(term: String): Resource<ListMeals> {
        return Resource.success(RetrofitClient.webService.searchListMealsBySearch(term))
    }

    suspend fun getMealsInformation(id:String):Resource<MealsInformation>{
        return Resource.success(RetrofitClient.webService.getInformationMeals(id))
    }
}