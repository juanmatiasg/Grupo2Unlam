package com.example.navigationdrawer.ui.detailFromHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.domain.Repo
import kotlinx.coroutines.launch

class DetailHomeViewModel(private val repo: Repo) : ViewModel() {



    fun insertMeal(meals: MealEntity){
        viewModelScope.launch {
            repo.insertMeal(meals)
        }

    }

    fun insertMealPlanner(meal: PlannerEntity){
        viewModelScope.launch {
            repo.insertMealPlanner(meal)
        }
    }




}