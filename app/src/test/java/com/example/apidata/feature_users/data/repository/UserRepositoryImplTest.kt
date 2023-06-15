package com.example.apidata.feature_users.data.repository

import com.example.apidata.feature_users.data.remote.UserApi
import com.example.apidata.feature_users.domain.UserDumpData
import com.example.apidata.feature_users.domain.model.User
import com.example.apidata.feature_users.domain.repository.UserRepository
import com.example.apidata.feature_users.domain.uitl.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class UserRepositoryImplTest {

    private lateinit var userRepository: UserRepository
    private lateinit var mockWebServer : MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val userApi = retrofit.create(UserApi::class.java)
        userRepository = UserRepositoryImpl(userApi)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getRandomUserWhenSuccess`() = runBlocking{
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(UserDumpData.result)
        )

        var user : User?=null
        var errorMessage = ""
        userRepository.getRandomUser().collectLatest {
            user = it.data
            errorMessage = it.message?:""
        }
        assertTrue(user !=  null)
        assertTrue(errorMessage.isEmpty())
    }
    @Test
    fun `getRandomUserWhenFailed`() = runBlocking{
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(404)
                .setBody("{}")
        )

        var user : User?=null
        var errorMessage = ""
        userRepository.getRandomUser().collectLatest {
            user = it.data
            errorMessage = it.message?:""
        }
        assertTrue(user ==  null)
        assertTrue(errorMessage.isNotEmpty())
    }
}