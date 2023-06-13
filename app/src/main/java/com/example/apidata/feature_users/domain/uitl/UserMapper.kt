package com.example.apidata.feature_users.domain.uitl

import com.example.apidata.feature_users.data.remote.UserDto
import com.example.apidata.feature_users.domain.model.User

fun UserDto.toUser() : User {
    return User(
        id= id,
        username = username,
        gender = gender,
        phoneNumber = phone_number,
        dateOfBirth = date_of_birth,
        email = email,
        avatar = avatar,
        lat = address.coordinates.lat,
        lng = address.coordinates.lng
    )
}
