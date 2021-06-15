package com.example.navigationdrawer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.domain.MealDao
import java.util.*

@Database(

        version = 1,
        entities = [MealEntity::class]
)
abstract class AppDataBase:RoomDatabase(){
    abstract fun mealDao():MealDao


    companion object{
        private var INSTANCE: AppDataBase?=null
        val MIGRATION_1_2=object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE MealEntity" +
                        "ALTER COLUMN id VARCHAR(40)")
            }

        }

        fun getDatabase(context:Context):AppDataBase{
            INSTANCE=INSTANCE ?: Room.databaseBuilder(context.applicationContext,AppDataBase::class.java,"MealEntity").build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE=null
        }

    }
}