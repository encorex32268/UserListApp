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
       UserState()
    )

    init {
        getDataBySize(20)
    }
    private fun getDataBySize(size : Int = 20){
        viewModelScope.launch {
            userRepository.getUserBySize(size = size.toString()).collectLatest {
                when(it){
                    is Resource.Loading->{
                        state = state.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error->{
                        state = state.copy(
                            isLoading = false
                        )
                    }
                    is Resource.Success->{
                        it.data?.let {
                            state = state.copy(
                                users = it,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }



}