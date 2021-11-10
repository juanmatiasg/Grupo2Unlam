package com.example.navigationdrawer.domain

import androidx.room.*
import com.example.navigationdrawer.data.entities.*


@Dao
interface MealDao {

    @Query("SELECT * FROM table_meals")
    suspend fun getListMeal(): List<MealEntity>

    @Query("SELECT * FROM table_planner")
    suspend fun getListPlanner(): List<PlannerEntity>

    @Query("SELECT * FROM breakfast_table")
    suspend fun getBreakfast(): List<BreakfastEntity>

    @Query("SELECT * FROM lunch_entity")
    suspend fun getLunch(): List<LunchEntity>

    @Query("SELECT * FROM afternoonsnack_table")
    suspend fun getAfternoonSnack(): List<AfternoonSnackEntity>

    @Query("SELECT * FROM dinner_table")
    suspend fun getDinner(): List<DinnerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealPlanner(meal: PlannerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreakfast(meal: BreakfastEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLunch(meal: LunchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAfternoonSnack(meal: AfternoonSnackEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDinner(meal: DinnerEntity)


    @Delete
    suspend fun deleteMealFromFavourites(meal: MealEntity)

    @Delete
    suspend fun deleleFromPlanner(meal: PlannerEntity)

    @Delete
    suspend fun deleteBreakfast(meal: BreakfastEntity)

    @Delete
    suspend fun deleteLunch(meal: LunchEntity)

    @Delete
    suspend fun deleteAfternoonSnack(meal: AfternoonSnackEntity)

    @Delete
    suspend fun deleteDinner(meal: DinnerEntity)


    @Query("DELETE FROM table_planner")
    suspend fun deleteAllPlanner()


    @Query("DELETE FROM breakfast_table")
    suspend fun deleteAllBreakfast()

    @Query("DELETE FROM lunch_entity")
    suspend fun deleteAllLunch()

    @Query("DELETE FROM afternoonsnack_table")
    suspend fun deleteAllAfternoonSnack()

    @Query("DELETE FROM dinner_table")
    suspend fun deleteAllDinner()

}