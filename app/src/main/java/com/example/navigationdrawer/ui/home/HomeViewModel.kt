package com.example.navigationdrawer.ui.home

import androidx.lifecycle.*
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class HomeViewModel(private val repo: Repo) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

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
}