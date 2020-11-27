package com.example.meepappsample.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meepappsample.model.ResponseModel
import com.example.meepappsample.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapsModelView @ViewModelInject constructor(private val repository: RemoteRepository) :
    ViewModel() {

    private val _locationResponse = MutableLiveData<List<ResponseModel>>()

    val locationResponse: LiveData<List<ResponseModel>>
        get() = _locationResponse

    init {
        getLocations()
    }

    private fun getLocations()  = viewModelScope.launch(Dispatchers.IO) {
        try {
           _locationResponse.postValue(repository.getLocations())
        } catch (e: Exception) {

        }
    }
}