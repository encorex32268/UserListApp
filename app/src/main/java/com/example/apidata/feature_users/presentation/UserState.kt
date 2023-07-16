package com.example.apidata.feature_users.presentation

import com.example.apidata.feature_users.domain.model.User

data class UserState(
    val users :  List<User> = emptyList(),
    val isLoading : Boolean = true
)
