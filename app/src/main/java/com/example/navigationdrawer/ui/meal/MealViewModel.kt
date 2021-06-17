package com.example.navigationdrawer.ui.meal

import androidx.lifecycle.*
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MealViewModel(private val repo: Repo) : ViewModel() {

    private val term = MutableLiveData<String>()

    init {
        setComida("chicken")
    }

    val fetchMeals = term.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(repo.getListMeals(it))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Error Ocurred"))

            }
        }
    }

    fun setComida(otroTerm: String) {
        term.value = otroTerm
    }

    /*Agregado*/
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