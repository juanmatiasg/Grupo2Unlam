package com.example.navigationdrawer.ui.home

import androidx.lifecycle.*
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.entities.PlannerEntity
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val repo: Repo) : ViewModel() {

    fun getMealsHome() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(repo.getMealsHome())
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }

    fun deleteFromPlanner(plannerEntity: PlannerEntity){
        viewModelScope.launch {
            repo.deleteFromPlanner(plannerEntity)
        }
    }

    fun deleteAllPlanner(){
        viewModelScope.launch {
            repo.deleteAllPlanner()
        }
    }

    /*FindNavControler
    val fetchListMeal= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(repo.getListMealByB())
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Ocurred"))

        }
    }*/

}