package com.example.navigationdrawer.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers

class GalleryViewModel(private val repo: Repo):ViewModel(){
    fun getMealsFavoritos() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(repo.getMealsFavoritos())
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Ocurrio un error"))
        }
    }
}
