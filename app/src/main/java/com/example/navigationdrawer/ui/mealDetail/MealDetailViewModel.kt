package com.example.navigationdrawer.ui.mealDetail

import androidx.lifecycle.*
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealDetailViewModel(private val repo: Repo) : ViewModel() {



    fun insertMeal(meals:MealEntity){
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