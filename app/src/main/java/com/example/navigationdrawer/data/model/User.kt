package com.example.navigationdrawer.data.model

import androidx.annotation.DrawableRes

data class User(
    var name: String,
    var surname: String,
    var dateOfBirth: String,
    var weight: String,
    var height: String,
    var gender: String,
    @DrawableRes val image: Int,
)