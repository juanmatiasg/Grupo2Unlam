package com.example.navigationdrawer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.navigationdrawer.data.entities.*
import com.example.navigationdrawer.domain.MealDao

@Database(
    version = 1,
    entities = [MealEntity::class, PlannerEntity::class,BreakfastEntity::class,LunchEntity::class,AfternoonSnackEntity::class,DinnerEntity::class],
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun mealDao(): MealDao

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "DBMeals_API_v2.0.29"
            )
                .build()
            return INSTANCE!!
        }
    }


}