package com.example.navigationdrawer.data

import android.app.Application
import android.widget.Toast
import com.example.navigationdrawer.data.database.AppDataBase
import com.example.navigationdrawer.data.entities.*
import com.example.navigationdrawer.data.model.DirectionsDto
import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.data.model.MealsInformation
import com.example.navigationdrawer.vo.MapsRetrofitClient
import com.example.navigationdrawer.vo.Resource
import com.example.navigationdrawer.vo.RetrofitClient
import com.example.navigationdrawer.vo.toUrlParam
import com.google.android.gms.maps.model.LatLng
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

    suspend fun insertBreakfast(meal: BreakfastEntity){
        appDataBase.mealDao().insertBreakfast(meal)
    }

    suspend fun insertLunch(meal: LunchEntity){
        appDataBase.mealDao().insertLunch(meal)
    }

    suspend fun insertAfternoonSnack(meal: AfternoonSnackEntity){
        appDataBase.mealDao().insertAfternoonSnack(meal)
    }

    suspend fun insertDinner(meal: DinnerEntity){
        appDataBase.mealDao().insertDinner(meal)
    }

    suspend fun getMealsFavoritos(): Resource<List<MealEntity>> {
        return Resource.success(appDataBase.mealDao().getListMeal())
    }

    suspend fun getBreakfast(): Resource<List<BreakfastEntity>>{
        return Resource.success(appDataBase.mealDao().getBreakfast())
    }

    suspend fun getLunch(): Resource<List<LunchEntity>>{
        return Resource.success(appDataBase.mealDao().getLunch())
    }

    suspend fun getAfternoonSnack(): Resource<List<AfternoonSnackEntity>>{
        return Resource.success(appDataBase.mealDao().getAfternoonSnack())
    }

    suspend fun getDinner(): Resource<List<DinnerEntity>>{
        return Resource.success(appDataBase.mealDao().getDinner())
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

    suspend fun deleteBreakfast(meal: BreakfastEntity){
        appDataBase.mealDao().deleteBreakfast(meal)
    }

    suspend fun deleteLunch(meal: LunchEntity){
        appDataBase.mealDao().deleteLunch(meal)
    }

    suspend fun deleteAfternoonSnack(meal: AfternoonSnackEntity){
        appDataBase.mealDao().deleteAfternoonSnack(meal)
    }

    suspend fun deleteDinner(meal: DinnerEntity){
        appDataBase.mealDao().deleteDinner(meal)
    }

    suspend fun deleteAllPlanner() {
        appDataBase.mealDao().deleteAllPlanner()
    }

    suspend fun deleteAllBreakfast(){
        appDataBase.mealDao().deleteAllBreakfast()
    }

    suspend fun deleteAllLunch(){
        appDataBase.mealDao().deleteAllLunch()
    }

    suspend fun deleteAllAfternoonSnack() {
        appDataBase.mealDao().deleteAllAfternoonSnack()
    }

    suspend fun deleteAllDinner(){
        appDataBase.mealDao().deleteAllDinner()
    }

    suspend fun retrieveDirections(origin: LatLng, destination: LatLng):DirectionsDto{
        return MapsRetrofitClient.getDirectionsApi().getDirections(origin.toUrlParam(), destination.toUrlParam())
    }




}