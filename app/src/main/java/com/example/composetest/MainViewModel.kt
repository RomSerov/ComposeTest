package com.example.composetest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.data.model.Movies
import com.example.composetest.data.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val _allMovies = MutableLiveData<List<Movies>>()
    val allMovies: LiveData<List<Movies>> get() = _allMovies

    fun getAllMovies() {
        viewModelScope.launch {
            repository.getAllMovies().let {
                if (it.isSuccessful) {
                    _allMovies.postValue(it.body())
                } else {
                    Log.d("checkData", "Ошибка загрузки ${it.errorBody()}")
                }
            }
        }
    }
}