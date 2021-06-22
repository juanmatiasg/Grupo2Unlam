package com.example.navigationdrawer.domain

import androidx.room.*
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity


@Dao
interface MealDao {

    @Query("SELECT * FROM table_meals")
    suspend fun getListMeal(): List<MealEntity>

    @Query("SELECT * FROM table_planner")
    suspend fun getListPlanner(): List<PlannerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealPlanner(meal: PlannerEntity)

    @Delete
    suspend fun deleteMeal(meal: MealEntity)

    @Delete
    suspend fun deleleFromPlanner(meal: PlannerEntity)

}