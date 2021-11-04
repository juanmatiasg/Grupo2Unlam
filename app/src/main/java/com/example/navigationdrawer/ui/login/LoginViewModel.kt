package com.example.navigationdrawer.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.navigationdrawer.data.entities.SaveEmailEntity
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.lang.Exception

class LoginViewModel(private val repo: Repo) : ViewModel() {

    fun saveEmail(email: SaveEmailEntity){
        viewModelScope.launch {
            repo.insertSaveEmail(email)
        }
    }
}