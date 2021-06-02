package com.example.navigationdrawer.ui.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MealViewModel(private val repo: Repo): ViewModel() {

    val fetchMeals = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(repo.getListMeals())
        }
        catch (e:Exception){
            emit(Resource.error(data = null,message = e.message ?: "Error Ocurred"))

        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is meal Fragment"
    }
    val text: LiveData<String> = _text
}