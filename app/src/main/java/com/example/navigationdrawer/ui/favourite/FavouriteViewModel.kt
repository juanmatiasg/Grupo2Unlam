package com.example.navigationdrawer.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repo: Repo):ViewModel(){
    fun getMealsFavoritos() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(repo.getMealsFavoritos())
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }

    fun deleteFavourite(mealEntity: MealEntity){
        viewModelScope.launch {
            repo.deleteFavourite(mealEntity)
        }
    }


}
