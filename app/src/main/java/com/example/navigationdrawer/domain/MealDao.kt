package com.example.navigationdrawer.domain

import androidx.room.*
import com.example.navigationdrawer.data.entities.MealEntity


@Dao
interface MealDao {

    @Query("SELECT * FROM MealEntity")
    suspend fun getListMealFavourites(): List<MealEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity)

    @Delete
    suspend fun deleteMeal(meal: MealEntity)


}