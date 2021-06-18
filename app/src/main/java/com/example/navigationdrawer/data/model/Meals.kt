package com.example.navigationdrawer.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meals(
    @SerializedName("idMeal")
    var id:String,
    @SerializedName("strMeal")
    var title: String,
    @SerializedName("strMealThumb")
    var image: String,
    @SerializedName("strInstructions")
    var description: String,
    @SerializedName("protein")
    var protein: String,
    @SerializedName("strYoutube")
    var strYoutube: String
) : Parcelable
