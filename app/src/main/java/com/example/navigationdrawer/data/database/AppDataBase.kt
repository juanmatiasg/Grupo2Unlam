package com.example.navigationdrawer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.domain.MealDao

@Database(
        version = 1,
        entities = [MealEntity::class]
)
abstract class AppDataBase:RoomDatabase(){
    abstract fun mealDao():MealDao

    companion object{
        private var INSTANCE: AppDataBase?=null

        fun getDatabase(context:Context):AppDataBase{
            INSTANCE=INSTANCE ?: Room.databaseBuilder(context.applicationContext,AppDataBase::class.java,"MealEntity").build()
            return INSTANCE!!
        }
    }
}