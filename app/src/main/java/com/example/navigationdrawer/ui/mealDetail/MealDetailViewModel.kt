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
    /*Launch lanza una nueva corrutina sin bloquear el hilo actual hila de la vista
    y  devuelve una referencia a la corrutina como trabajo.*/



}