package com.example.navigationdrawer.ui.planner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.navigationdrawer.data.entities.*
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class PlannerViewModel(private val repo: Repo) : ViewModel() {

    /*
    fun getBreakfast() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(repo.getBreakfast())
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }

    fun deleteBreakfast(breakfastEntity: BreakfastEntity){
        viewModelScope.launch {
            repo.deleteBreakfast(breakfastEntity)
        }
    }

    fun getLunch() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(repo.getLunch())
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }

    fun deleteLunch(lunchEntity: LunchEntity){
        viewModelScope.launch {
            repo.deleteLunch(lunchEntity)
        }
    }

    fun getAfternoonSnack() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(repo.getAfternoonSnack())
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }

    fun deleteAfternoonSnack(afternoonSnackEntity: AfternoonSnackEntity){
        viewModelScope.launch {
            repo.deleteAfternoonSnack(afternoonSnackEntity)
        }
    }

    fun getDinner() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(repo.getDinner())
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }

    fun deleteDinner(dinnerEntity: DinnerEntity){
        viewModelScope.launch {
            repo.deleteDinner(dinnerEntity)
        }
    }*/
}