package com.example.navigationdrawer.ui.meal

import androidx.lifecycle.*
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class MealViewModel(private val repo: Repo) : ViewModel() {

    private val term = MutableLiveData<String>()


    /*
        tragosData es un MutableLiveData
        distinctUntilChanger () -
        distingir a nosotros en el buscador, y si buscamos nos van a traer una lista de comidas , pero si volvemos apretar
        el boton buscar ,
        no va a buscar de nuevo porque no ha cambiado ese valor de mutableLiveData ,
        por esoo el ultimo valor que ha buscado es distinto al valor que ha puesto en el SearchView
        el switchMap nos permite obtener esa informacion que tiene el MutableLiveData y retornar un LiveData
    */


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

    val fetchListMeal= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(repo.getListMealByB())
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Ocurred"))

        }
    }


    fun setComida(otroTerm: String) {
        term.value = otroTerm
    }


}