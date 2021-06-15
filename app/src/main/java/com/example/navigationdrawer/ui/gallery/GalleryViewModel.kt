package com.example.navigationdrawer.ui.gallery

import androidx.lifecycle.*
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class GalleryViewModel(private val repo: Repo) : ViewModel() {


    fun guardarComida(meal: MealEntity){
        viewModelScope.launch {
            repo.insertMeal(meal)
        }
    }

    fun getMealsFavourites()= liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(repo.getMealsFavoritos())
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }
}