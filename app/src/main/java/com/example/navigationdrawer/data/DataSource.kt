package com.example.navigationdrawer.data

import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.vo.Resource
import com.example.navigationdrawer.vo.RetrofitClient

class DataSource {
    suspend fun getListMeals(): Resource<ListMeals> {
        return Resource.success(RetrofitClient.webService.getListMeals())
    }
}