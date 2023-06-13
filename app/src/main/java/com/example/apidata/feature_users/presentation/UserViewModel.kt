package com.example.apidata.feature_users.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apidata.feature_users.domain.model.User
import com.example.apidata.feature_users.domain.repository.UserRepository
import com.example.apidata.feature_users.domain.uitl.Resource
import com.example.apidata.feature_users.domain.uitl.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){

    var state by mutableStateOf(
        listOf<User>()
    )
    private val _uiEvent = Channel<UiEvent>()
    var uiEvent = _uiEvent.receiveAsFlow()
    init {
        getDataBySize(20)
    }
    fun getDataBySize(size : Int = 20){
        viewModelScope.launch {
            userRepository.getUserBySize(size = size.toString()).collectLatest {
                when(it){
                    is Resource.Loading->{}
                    is Resource.Error->{
                        Log.d("TAG", "getDataBySize: ${it.message}")
                        _uiEvent.send(UiEvent.APIError)
                    }
                    is Resource.Success->{
                        it.data?.let {
                            state = it
                            Log.d("TAG", "getDataBySize: ${it}")
                        }
                    }
                }
            }
        }
    }



}