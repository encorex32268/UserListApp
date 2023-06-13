package com.example.apidata.feature_users.di

import com.example.apidata.feature_users.data.remote.UserApi
import com.example.apidata.feature_users.data.repository.UserRepositoryImpl
import com.example.apidata.feature_users.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun providesUserApi() : UserApi {
        return Retrofit.Builder()
            .baseUrl(UserApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }

    @Provides
    @Singleton
    fun providesUserRepository(
        userApi : UserApi
    ) : UserRepository {
        return UserRepositoryImpl(userApi)
    }
}