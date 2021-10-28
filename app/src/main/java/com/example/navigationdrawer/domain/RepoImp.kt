package com.example.navigationdrawer.domain

import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.data.model.MealsInformation
import com.example.navigationdrawer.vo.Resource

class RepoImp(private val dataSource: DataSource):Repo{

    override suspend fun getListMeals(term: String): Resource<ListMeals> {
        return dataSource.getListMeals(term)
    }


    override suspend fun getMealsFavoritos(): Resource<List<MealEntity>> {
        return dataSource.getMealsFavoritos()
    }

    override suspend fun insertMeal(meal: MealEntity) {
        dataSource.insertMeal(meal)
    }

    override suspend fun insertMealPlanner(meal: PlannerEntity) {
        dataSource.insertMealPlanner(meal)
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

    override suspend fun deleteAllPlanner() {
        dataSource.deleteAllPlanner()
    }




}