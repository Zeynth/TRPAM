package edu.uksw.fti.pam.taspam.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import coil.compose.rememberImagePainter
import edu.uksw.fti.pam.taspam.R
import edu.uksw.fti.pam.taspam.model.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    popularViewModel: SearchViewModel,
    recommendViewModel: JatengViewModel,
    navController: NavController
) {
//    val context = LocalContext.current
//    val scrollState = rememberScrollState()
    LaunchedEffect(
        Unit,
        block = {
            popularViewModel.getSearchList()
            recommendViewModel.getList()
        }
    )
    Column(
        modifier = Modifier
            .verticalScroll((rememberScrollState()))
            .height(1050.dp)) {
        Column{
            TopAppBar(
                modifier=Modifier.height(60.dp),
                backgroundColor = Color(231,79,80),
                contentColor = Color.Black,
            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(20.dp),
            ) {
                Card(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.onoroso),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
            Column() {
                Text(
                    text = stringResource(id = R.string.popular),
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp),
                    fontWeight = FontWeight.W500,
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                )
                when {
                    popularViewModel.errorMessage.isEmpty() -> {
                        PopularHomeList(list = popularViewModel.searchList) { id, title, image, bahan, desc ->
                            Log.d("ClickItem", "this is id: $id")
                            navController.navigate("Detail?id=$id?title=$title?image=$image?bahan=$bahan?desc=$desc")
                        }
                    }
                    else -> Log.e("AVM", "Something happened")
                }

                Text(
                    text = stringResource(id = R.string.recommend),
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp),
                    fontWeight = FontWeight.W500,
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                )
                when {
                    popularViewModel.errorMessage.isEmpty() -> {
                        HomeList(list = recommendViewModel.List) { id, title, image, bahan, desc ->
                            Log.d("ClickItem", "this is id: $id")
                            navController.navigate("Detail?id=$id?title=$title?image=$image?bahan=$bahan?desc=$desc")
                        }
                    }
                    else -> Log.e("AVM", "Something happened")
                }
            }
        }
    }
}

@Composable
fun PopularHomeList(list: List<Model>, itemClick: (index: Int, title: String, imgUrl: String, bahan: String, Deskripsi:String)-> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)

    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(list) { index, item ->
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                        .padding(5.dp)
                        .clickable {
                            itemClick(item.id, item.title, item.image, item.bahan, item.desc)
                        },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(5.dp),

//                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column() {
                            Image(
                                painter = rememberImagePainter(
                                    data = item.image,
                                    builder = {
//                                    scale(Scale.FILL)
//                                    placeholder(coil.base.R.drawable.notification_action_background)
//                                    transformations(CircleCropTransformation())
                                    }
                                ),
                                contentScale = ContentScale.FillBounds,
                                contentDescription = item.desc,
                                modifier = Modifier
                                    .height(90.dp)
                                    .height(90.dp)
                            )
                            Text(text = item.title)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeList(list: List<Model>, itemClick: (index: Int, title: String, imgUrl: String, bahan: String, Deskripsi:String)-> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)


    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(list) { index, item ->
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                        .padding(5.dp)
                        .clickable {
                            itemClick(item.id, item.title, item.image, item.bahan, item.desc)
                        },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(5.dp),

//                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column() {
                            Image(
                                painter = rememberImagePainter(
                                    data = item.image,
                                    builder = {
//                                    scale(Scale.FILL)
//                                    placeholder(coil.base.R.drawable.notification_action_background)
//                                    transformations(CircleCropTransformation())
                                    }
                                ),
                                contentScale = ContentScale.FillBounds,
                                contentDescription = item.desc,
                                modifier = Modifier
                                    .height(90.dp)
                                    .height(90.dp)
                            )
                            Text(text = item.title)
                        }
                    }
                }
            }
        }
    }
}