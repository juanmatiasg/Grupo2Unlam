package com.example.navigationdrawer.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable
