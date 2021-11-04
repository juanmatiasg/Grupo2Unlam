package com.example.navigationdrawer.domain

import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.data.entities.SaveEmailEntity
import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.data.model.MealsInformation
import com.example.navigationdrawer.vo.Resource

interface Repo {
    suspend fun getListMeals(term: String): Resource<ListMeals>


    suspend fun getMealsFavoritos(): Resource<List<MealEntity>>

    suspend fun insertMeal(meal: MealEntity)

    suspend fun insertMealPlanner(meal: PlannerEntity)

    suspend fun getListMealByB(): Resource<ListMeals>

    suspend fun getMealsHome(): Resource<List<PlannerEntity>>

    suspend fun deleteFavourite(meal: MealEntity)

    suspend fun deleteFromPlanner(meal: PlannerEntity)

    suspend fun deleteAllPlanner()

    suspend fun insertSaveEmail(email: SaveEmailEntity)

}
