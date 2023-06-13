package com.example.apidata.feature_users.domain.repository

import com.example.apidata.feature_users.domain.model.User
import com.example.apidata.feature_users.domain.uitl.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getRandomUser(): Flow<Resource<User>>

    suspend fun getUserBySize(size : String) : Flow<Resource<List<User>>>
}