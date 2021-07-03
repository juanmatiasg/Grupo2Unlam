package com.example.navigationdrawer.ui.detalFavourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.domain.Repo
import kotlinx.coroutines.launch

class FavouriteDetailViewModel(private val repo: Repo) : ViewModel() {


    fun deleteMeal(meals: MealEntity) {
        viewModelScope.launch {
            repo.deleteFavourite(meals)
        }

    }

    fun insertMealPlanner(meal: PlannerEntity) {
        viewModelScope.launch {
            repo.insertMealPlanner(meal)
        }
    }
}