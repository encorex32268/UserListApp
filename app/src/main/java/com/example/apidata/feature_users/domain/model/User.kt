package com.example.apidata.feature_users.domain.model

data class User(
    val id : Int,
    val username : String,
    val gender : String,
    val phoneNumber : String,
    val dateOfBirth : String,
    val email : String,
    val avatar : String,
    val lat : Double,
    val lng : Double
)
