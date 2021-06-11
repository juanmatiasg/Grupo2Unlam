package com.example.navigationdrawer.data.model

import com.google.gson.annotations.SerializedName

data class Meals(
    @SerializedName("id")
    var id:String,
    @SerializedName("title")
    var title: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("calories")
    var calories: String,
    @SerializedName("protein")
    var protein: String

)
