package com.example.navigationdrawer.data

import android.app.Application
import android.widget.Toast
import com.example.navigationdrawer.data.database.AppDataBase
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.data.entities.SaveEmailEntity
import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.data.model.MealsInformation
import com.example.navigationdrawer.vo.Resource
import com.example.navigationdrawer.vo.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import java.util.concurrent.Executors

class DataSource(private val appDataBase: AppDataBase) {

    suspend fun getListMeals(term: String): Resource<ListMeals> {
        return Resource.success(RetrofitClient.webService.searchListMealsBySearch(term))
    }


    suspend fun insertMeal(meal: MealEntity) {
        appDataBase.mealDao().insertMeal(meal)
    }

    suspend fun insertMealPlanner(meal: PlannerEntity) {
        appDataBase.mealDao().insertMealPlanner(meal)
    }

    suspend fun getMealsFavoritos(): Resource<List<MealEntity>> {
        return Resource.success(appDataBase.mealDao().getListMeal())
    }

    suspend fun getListMealByB(): Resource<ListMeals> {
        return Resource.success(RetrofitClient.webService.getListMeal())
    }

    suspend fun getMealsHome(): Resource<List<PlannerEntity>> {
        return Resource.success(appDataBase.mealDao().getListPlanner())
    }

    suspend fun deleteFavourite(meal: MealEntity) {
        appDataBase.mealDao().deleteMealFromFavourites(meal)
    }

    suspend fun deleteFromPlanner(meal: PlannerEntity) {
        appDataBase.mealDao().deleleFromPlanner(meal)
    }

    suspend fun deleteAllPlanner() {
        appDataBase.mealDao().deleteAllPlanner()
    }

    suspend fun insertSaveEmail(email: SaveEmailEntity) {
        appDataBase.mealDao().insertSaveEmail(email)
    }


}