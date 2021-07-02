package com.example.navigationdrawer.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_planner")
data class PlannerEntity(

    @PrimaryKey
    @ColumnInfo(name="id")
    var id:String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "description")
    var description: String



)