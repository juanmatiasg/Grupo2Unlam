package com.example.navigationdrawer.ui.mealDetail

import androidx.lifecycle.*
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealDetailViewModel(private val repo: Repo) : ViewModel() {

    private val idInformationMeals = MutableLiveData<String>()

    /*
    val fetchMealsInformation = idInformationMeals.switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(repo.getMealsInformation(it))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
            }
        }
    }*/

    fun setIdInformation(id: String) {
        idInformationMeals.value = id
    }

    fun insertMeal(meals:MealEntity){
        viewModelScope.launch {
            repo.insertMeal(meals)
        }

    }


    fun getMealsFavoritos() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(repo.getMealsFavoritos())
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }

}