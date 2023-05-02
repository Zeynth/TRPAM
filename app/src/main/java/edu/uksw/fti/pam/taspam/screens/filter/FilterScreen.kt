package edu.uksw.fti.pam.taspam.screens.filter

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uksw.fti.pam.taspam.R
import edu.uksw.fti.pam.taspam.screens.Screens

@Composable
fun FilterScreen(
    navController: NavController
) {
    Column (modifier = Modifier
        .verticalScroll((rememberScrollState()))
        .height(1050.dp)
    ){
        TopAppBar(modifier=Modifier.height(60.dp),
            backgroundColor = Color(231,79,80),
            contentColor = Color.Black,
        ){
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.area),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontSize = 20.sp,
                )
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { navController.navigate(route = Screens.NTBScreen.route) }) {
            Image(
                painter = painterResource(id = R.drawable.khasntb),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Text(text = stringResource(id = R.string.ntbfood))
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { navController.navigate(route = Screens.JatengScreen.route) }) {
            Image(
                painter = painterResource(id = R.drawable.khassmg),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Text(text = stringResource(id = R.string.cjavafood))
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { navController.navigate(route = Screens.SumbarScreen.route) }) {
            Image(
                painter = painterResource(id = R.drawable.khassumbar),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Text(text = stringResource(id = R.string.wsumatrafood))
        }
//        when {
//            recommendViewModel.errorMessage.isEmpty() -> {
//                RecommendList(list = recommendViewModel.recommendList) { id, title, image, bahan, desc ->
//                    Log.d("ClickItem", "this is id: $id")
//                    navController.navigate("Detail?id=$id?title=$title?image=$image?bahan=$bahan?desc=$desc")
//                }
//            }
//            else -> Log.e("AVM", "Something happened")
//        }
    }
}