package com.example.navigationdrawer.ui.help

import androidx.lifecycle.*
import com.example.navigationdrawer.data.model.DirectionsDto
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.vo.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HelpViewModel(val repo: Repo) : ViewModel() {

    val currentDirections = MutableLiveData<DirectionsDto>(null)

    fun retrieveDirections(origin: LatLng, destination: LatLng) {
        viewModelScope.launch {
            var directions = repo.retrieveDirections(origin, destination)
            currentDirections.value = directions
        }
    }


}