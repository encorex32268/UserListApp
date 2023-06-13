package com.example.apidata.feature_users.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.apidata.R
import com.example.apidata.feature_users.domain.model.User
import com.example.apidata.ui.theme.APIDataTheme

@Composable
fun UserScreen(
    users : List<User>
) {
    LazyColumn{
        items(users){user ->
            UserItem(user)
        }
    }
}

@Composable
fun UserItem(
    user: User,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .size(80.dp),
            model = user.avatar,
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()

        ) {
            Row{
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = when(user.gender){
                                "Male" -> R.drawable.baseline_man_24
                                "Female" -> R.drawable.baseline_woman_24
                                else->{
                                    R.drawable.baseline_question_mark_24
                                }
                            }
                    ),
                    tint = Color.Unspecified,
                    contentDescription = "gender mark"
                )
                Text(text = user.username)
            }
            Text(text = user.dateOfBirth)
            Text(text = user.email)
            Text(text = user.phoneNumber)


        }
    }

}




@Composable
@Preview(showSystemUi = true)
fun UserItemPV() {
    APIDataTheme {
        UserItem(
            user = User(
                id = 0,
                username = "Lee Chen",
                gender = "Male",
                phoneNumber = "0930312314",
                dateOfBirth = "0822",
                email = "em@gmail.com",
                avatar = "222",
                lat = 23.4335,
                lng = 23.3
            ),
        )
    }
}