package com.example.apidata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apidata.feature_users.presentation.UserScreen
import com.example.apidata.feature_users.presentation.UserViewModel
import com.example.apidata.ui.theme.APIDataTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APIDataTheme {
                val viewModel = hiltViewModel<UserViewModel>()
                val state = viewModel.state
                UserScreen(users = state)
            }
        }
    }
}
