package com.example.navigationdrawer.domain

import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.entities.*
import com.example.navigationdrawer.data.model.DirectionsDto
import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.data.model.MealsInformation
import com.example.navigationdrawer.vo.MapsRetrofitClient
import com.example.navigationdrawer.vo.Resource
import com.example.navigationdrawer.vo.toUrlParam
import com.google.android.gms.maps.model.LatLng

class RepoImp(private val dataSource: DataSource):Repo{

    override suspend fun getListMeals(term: String): Resource<ListMeals> {
        return dataSource.getListMeals(term)
    }


    override suspend fun getMealsFavoritos(): Resource<List<MealEntity>> {
        return dataSource.getMealsFavoritos()
    }

    override suspend fun getBreakfast(): Resource<List<BreakfastEntity>> {
        return dataSource.getBreakfast()
    }

    override suspend fun getLunch(): Resource<List<LunchEntity>> {
        return dataSource.getLunch()
    }

    override suspend fun getAfternoonSnack(): Resource<List<AfternoonSnackEntity>> {
        return dataSource.getAfternoonSnack()
    }

    override suspend fun getDinner(): Resource<List<DinnerEntity>> {
        return dataSource.getDinner()
    }

    override suspend fun insertMeal(meal: MealEntity) {
        dataSource.insertMeal(meal)
    }

    override suspend fun insertMealPlanner(meal: PlannerEntity) {
        dataSource.insertMealPlanner(meal)
    }

    override suspend fun insertMealBreakfast(meal: BreakfastEntity) {
        dataSource.insertBreakfast(meal)
    }

    override suspend fun insertMealLunch(meal: LunchEntity) {
        dataSource.insertLunch(meal)
    }

    override suspend fun insertMealAfternoonSnack(meal: AfternoonSnackEntity) {
        dataSource.insertAfternoonSnack(meal)
    }

    override suspend fun insertMealDinner(meal: DinnerEntity) {
        dataSource.insertDinner(meal)
    }

    override suspend fun getListMealByB():Resource<ListMeals>{
        return dataSource.getListMealByB()
    }


    override suspend fun getMealsHome(): Resource<List<PlannerEntity>> {
        return dataSource.getMealsHome()
    }

    override suspend fun deleteFavourite(meal: MealEntity) {
        dataSource.deleteFavourite(meal)
    }

    override suspend fun deleteFromPlanner(meal: PlannerEntity) {
        dataSource.deleteFromPlanner(meal)
    }

    override suspend fun deleteBreakfast(meal: BreakfastEntity) {
        dataSource.deleteBreakfast(meal)
    }

    override suspend fun deleteAllBreakfast() {
        dataSource.deleteAllBreakfast()
    }

    override suspend fun deleteLunch(meal: LunchEntity) {
        dataSource.deleteLunch(meal)
    }

    override suspend fun deleteAllLunch() {
        dataSource.deleteAllLunch()
    }

    override suspend fun deleteAfternoonSnack(meal: AfternoonSnackEntity) {
        dataSource.deleteAfternoonSnack(meal)
    }

    override suspend fun deleteAllAfternoonSnack() {
        dataSource.deleteAllAfternoonSnack()
    }

    override suspend fun deleteDinner(meal: DinnerEntity) {
        dataSource.deleteDinner(meal)
    }

    override suspend fun deleteAllDinner() {
        dataSource.deleteAllDinner()
    }

    override suspend fun deleteAllPlanner() {
        dataSource.deleteAllPlanner()
    }

    override suspend fun retrieveDirections(origin: LatLng, destination: LatLng): DirectionsDto {
       return dataSource.retrieveDirections(origin, destination)
    }


}