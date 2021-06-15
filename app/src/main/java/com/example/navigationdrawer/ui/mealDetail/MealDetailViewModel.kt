package com.example.navigationdrawer.ui.mealDetail

import androidx.lifecycle.*
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MealDetailViewModel(private val repo: Repo) : ViewModel()  {

    private val term = MutableLiveData<String>()


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

    fun guardarComida(meal: MealEntity){
        viewModelScope.launch {
            repo.insertMeal(meal)
        }
    }
}