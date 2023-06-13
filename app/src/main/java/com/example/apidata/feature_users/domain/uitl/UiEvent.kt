package com.example.apidata.feature_users.domain.uitl

sealed class UiEvent{

    object APIError : UiEvent()
    object Success : UiEvent()

}
