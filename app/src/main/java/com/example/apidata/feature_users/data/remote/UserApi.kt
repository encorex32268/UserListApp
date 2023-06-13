package com.example.apidata.feature_users.data.remote

import com.example.apidata.feature_users.domain.uitl.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getRandomUser() : Response<UserDto>

    @GET("users")
    suspend fun getUserBySize(
        @Query("size") size : String,
    ) : Response<List<UserDto>>

    companion object{
        const val BASE_URL = "https://random-data-api.com/api/v2/"
    }

}