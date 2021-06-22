package com.example.navigationdrawer.data

import com.example.navigationdrawer.data.database.AppDataBase
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.MealsInformation
import com.example.navigationdrawer.vo.Resource
import com.example.navigationdrawer.vo.RetrofitClient

class DataSource(private val appDataBase: AppDataBase) {
    suspend fun getListMeals(term: String): Resource<ListMeals> {
        return Resource.success(RetrofitClient.webService.searchListMealsBySearch(term))
    }
    /*
    suspend fun getMealsInformation(id:String):Resource<MealsInformation>{
        return Resource.success(RetrofitClient.webService.getInformationMeals(id))
    }*/

    suspend fun insertMeal(meal:MealEntity){
        appDataBase.mealDao().insertMeal(meal)
    }
    suspend fun insertMealPlanner(meal:PlannerEntity){
        appDataBase.mealDao().insertMealPlanner(meal)
    }

    suspend fun getMealsFavoritos(): Resource<List<MealEntity>> {
        return Resource.success(appDataBase.mealDao().getListMeal())
    }

    suspend fun getListMealByB():Resource<ListMeals>{
        return Resource.success(RetrofitClient.webService.getListMeal())
    }

    suspend fun getMealsHome(): Resource<List<PlannerEntity>> {
        return Resource.success(appDataBase.mealDao().getListPlanner())
    }

}