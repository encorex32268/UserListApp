package com.example.apidata.feature_users.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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
import java.time.LocalDateTime

@Composable
fun UserScreen(
    state: UserState,
    modifier: Modifier = Modifier
) {
    if (state.isLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
        items(state.users){user ->
            UserItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                ,
                user = user
            )
        }
    })
}

@Composable
fun UserItem(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AsyncImage(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = when (user.gender) {
                        "Male" -> Color.Blue
                        "Female" -> Color.Red
                        else -> {
                            Color.Green
                        }
                    },
                    shape = CircleShape
                )
            ,
            model = user.avatar,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(8.dp))
        val age = getAgeFromDateOfBirth(user.dateOfBirth)
        Text(
            text = "${user.username},${age}"
        )

    }

//    Row(
//        modifier = modifier
//            .clip(RoundedCornerShape(10.dp))
//            .padding(8.dp)
//            .border(
//                width = 1.dp,
//                color = Color.Black,
//                shape = RoundedCornerShape(10.dp)
//            )
//    ) {
//        AsyncImage(
//            modifier = Modifier
//                .size(80.dp),
//            model = user.avatar,
//            contentDescription = null,
//        )
//        Column(
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth()
//
//        ) {
//            Row{
//                Icon(
//                    imageVector = ImageVector.vectorResource(
//                        id = when(user.gender){
//                                "Male" -> R.drawable.baseline_man_24
//                                "Female" -> R.drawable.baseline_woman_24
//                                else->{
//                                    R.drawable.baseline_question_mark_24
//                                }
//                            }
//                    ),
//                    tint = Color.Unspecified,
//                    contentDescription = "gender mark"
//                )
//                Text(text = user.username)
//            }
//            Text(text = user.dateOfBirth)
//            Text(text = user.email)
//            Text(text = user.phoneNumber)
//
//
//        }
//    }

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
                dateOfBirth = "1993-8-22",
                email = "em@gmail.com",
                avatar = "222",
                lat = 23.4335,
                lng = 23.3
            ),
        )
    }
}

private fun getAgeFromDateOfBirth(dateOfBirth : String) : String{
    val splits = dateOfBirth.split("-")
    val localTime = LocalDateTime.now()
    var age = localTime.year - splits[0].toInt()
    val month = splits[1].toInt()

    if(month.minus(localTime.monthValue) > 0){
        age -= 1
    }
    return age.toString()
}