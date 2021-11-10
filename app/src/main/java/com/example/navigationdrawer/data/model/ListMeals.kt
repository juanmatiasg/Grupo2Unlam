package com.example.navigationdrawer.data.model

import com.example.navigationdrawer.data.model.Meals
import com.google.gson.annotations.SerializedName

data class ListMeals(

    @SerializedName("meals")
    var meals:ArrayList<Meals>,

    /*
    @SerializedName("meals")
    var breakfast:ArrayList<Meals>,

    @SerializedName("meals")
    var lunch: ArrayList<Meals>,

    @SerializedName("meals")
    var afternoonSnack: ArrayList<Meals>,

    @SerializedName("meals")
    var dinner: ArrayList<Meals>*/

)