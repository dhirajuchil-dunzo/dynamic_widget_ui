package com.example.dynamiclayouts.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamiclayouts.Repo.LayoutRepoImpl
import com.example.dynamiclayouts.models.LayoutModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private  val TAG = "MainViewModel"
    private val layoutRepo = LayoutRepoImpl()
    private val _state = mutableStateOf(LayoutModel())
    val state: State<LayoutModel> = _state

   /* init {
        val v = layoutRepo.layouts["home_layout"]
        _state.value = v!!
    }*/


    fun getLayout(id: String) {
        viewModelScope.launch {
            val layout = layoutRepo.getLayout(id)
            _state.value = layout!!
        }
    }
}


