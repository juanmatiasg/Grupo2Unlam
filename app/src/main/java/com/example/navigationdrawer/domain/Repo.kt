package com.example.navigationdrawer.domain

import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.MealsInformation
import com.example.navigationdrawer.vo.Resource

interface Repo{
    suspend fun getListMeals(term: String):Resource<ListMeals>

    suspend fun getMealsInformation(id:String):Resource<MealsInformation>

    suspend fun getMealsFavoritos():Resource<List<MealEntity>>

    suspend fun insertMeal(meal:MealEntity)
}
