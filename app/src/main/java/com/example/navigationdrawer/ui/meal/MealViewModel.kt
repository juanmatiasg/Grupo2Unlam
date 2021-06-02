package com.example.navigationdrawer.ui.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MealViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is meal Fragment"
    }
    val text: LiveData<String> = _text
}