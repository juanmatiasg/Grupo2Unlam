package com.example.navigationdrawer.domain

import com.example.navigationdrawer.data.entities.*
import com.example.navigationdrawer.data.model.DirectionsDto
import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.data.model.MealsInformation
import com.example.navigationdrawer.vo.Resource
import com.google.android.gms.maps.model.LatLng

interface Repo{


    suspend fun getListMeals(term: String):Resource<ListMeals>

    suspend fun getMealsFavoritos():Resource<List<MealEntity>>

    suspend fun getBreakfast():Resource<List<BreakfastEntity>>

    suspend fun getLunch():Resource<List<LunchEntity>>

    suspend fun getAfternoonSnack():Resource<List<AfternoonSnackEntity>>

    suspend fun getDinner():Resource<List<DinnerEntity>>

    suspend fun insertMeal(meal:MealEntity)

    suspend fun insertMealPlanner(meal: PlannerEntity)

    suspend fun insertMealBreakfast(meal: BreakfastEntity)

    suspend fun insertMealLunch(meal: LunchEntity)

    suspend fun insertMealAfternoonSnack(meal: AfternoonSnackEntity)

    suspend fun insertMealDinner(meal: DinnerEntity)

    suspend fun getListMealByB():Resource<ListMeals>

    suspend fun getMealsHome():Resource<List<PlannerEntity>>

    suspend fun deleteFavourite(meal:MealEntity)

    suspend fun deleteFromPlanner(meal: PlannerEntity)

    suspend fun deleteBreakfast(meal: BreakfastEntity)

    suspend fun deleteAllBreakfast()

    suspend fun deleteLunch(meal: LunchEntity)

    suspend fun deleteAllLunch()

    suspend fun deleteAfternoonSnack(meal: AfternoonSnackEntity)

    suspend fun deleteAllAfternoonSnack()

    suspend fun deleteDinner(meal: DinnerEntity)

    suspend fun deleteAllDinner()

    suspend fun deleteAllPlanner()

    suspend fun retrieveDirections(origin: LatLng, destination: LatLng): DirectionsDto


}
