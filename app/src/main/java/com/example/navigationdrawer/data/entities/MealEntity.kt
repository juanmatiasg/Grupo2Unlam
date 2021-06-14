package com.example.navigationdrawer.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MealEntity")
data class MealEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id:Long,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "protein")
    var protein: String



)