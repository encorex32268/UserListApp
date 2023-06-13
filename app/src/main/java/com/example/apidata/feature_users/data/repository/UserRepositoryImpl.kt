package com.example.apidata.feature_users.data.repository

import com.example.apidata.feature_users.data.remote.UserApi
import com.example.apidata.feature_users.domain.model.User
import com.example.apidata.feature_users.domain.repository.UserRepository
import com.example.apidata.feature_users.domain.uitl.Resource
import com.example.apidata.feature_users.domain.uitl.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException


class UserRepositoryImpl(
    private val userApi: UserApi
) : UserRepository{

    override suspend fun getRandomUser(): Flow<Resource<User>> = flow{
       emit(Resource.Loading())
        try {
            val result = userApi.getRandomUser()
            if (result.isSuccessful){
                result.body()?.let {
                    emit(
                        Resource.Success(it.toUser())
                    )
                }
            }else{
                emit(
                    Resource.Error(
                        data = null,
                        message = "${result.errorBody()?.string()}"
                    )
                )
            }
        }catch (e : IOException){
            emit(
                Resource.Error(
                    data = null,
                    message = "IOE Error"
                )
            )
        }

    }

    override suspend fun getUserBySize(size: String): Flow<Resource<List<User>>> = flow{
        emit(
            Resource.Loading()
        )
        try {
            val result = userApi.getUserBySize(size)
            if (result.isSuccessful){
                result.body()?.let {
                    it.let {
                        emit(
                            Resource.Success(
                                data =it.map { userDto ->
                                    userDto.toUser()
                                }
                            )
                        )
                    }
                }
            }else{
                emit(
                    Resource.Error(
                        data = emptyList(),
                        message = result.errorBody()?.string()
                    )
                )
            }
        }catch (e : IOException){
            emit(
                Resource.Error(
                    data = null,
                    message = e.message
                )
            )
        }
    }
}