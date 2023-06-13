package com.example.apidata.feature_users.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id : Int ,
    val username : String ,
    val gender : String ,
    val phone_number : String ,
    val date_of_birth : String,
    val email : String,
    val avatar : String,
    val address: Address,
)
