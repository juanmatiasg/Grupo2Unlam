package com.example.navigationdrawer.data.model

import com.google.gson.annotations.SerializedName

data class Meals(

    @SerializedName("strMeal")
    var strMeal: String,
    @SerializedName("strMealThumb")
    var strMealThumb: String
)
