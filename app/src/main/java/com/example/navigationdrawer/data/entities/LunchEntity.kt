package com.example.navigationdrawer.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lunch_entity")
data class LunchEntity(
    @PrimaryKey
    @ColumnInfo(name="id")
    var id:String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "strYoutube")
    var strYoutube: String
)
