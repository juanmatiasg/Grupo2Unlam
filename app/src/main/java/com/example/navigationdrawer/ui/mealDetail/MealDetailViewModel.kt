package com.example.navigationdrawer.ui.mealDetail

import androidx.lifecycle.*
import com.example.navigationdrawer.data.entities.*
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

    fun insertBreakfast(meal: BreakfastEntity){
        viewModelScope.launch {
            repo.insertMealBreakfast(meal)
        }
    }

    fun insertLunch(meal: LunchEntity){
        viewModelScope.launch {
            repo.insertMealLunch(meal)
        }
    }

    fun insertAfternoonSnack(meal: AfternoonSnackEntity){
        viewModelScope.launch {
            repo.insertMealAfternoonSnack(meal)
        }
    }

    fun insertDinner(meal: DinnerEntity){
        viewModelScope.launch {
            repo.insertMealDinner(meal)
        }
    }
}