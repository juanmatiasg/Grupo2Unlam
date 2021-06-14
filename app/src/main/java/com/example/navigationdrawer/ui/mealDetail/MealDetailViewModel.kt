package com.example.navigationdrawer.ui.mealDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers

class MealDetailViewModel (private val repo:Repo):ViewModel(){

    private val idInformationMeals = MutableLiveData<String>()

    val fetchMealsInformation = idInformationMeals.switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(repo.getMealsInformation(it))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
            }
        }
    }

    fun setIdInformation(id:String){
        idInformationMeals.value = id
    }
}