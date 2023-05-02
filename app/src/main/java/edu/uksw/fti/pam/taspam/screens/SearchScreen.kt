package edu.uksw.fti.pam.taspam.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import edu.uksw.fti.pam.taspam.R
import edu.uksw.fti.pam.taspam.model.*

@Composable
fun SearchScreen(
    searchViewModel : SearchViewModel,
    navController : NavController
) {
    var search by remember { mutableStateOf("") }
    LaunchedEffect(
        Unit,
        block = {
            searchViewModel.getSearchList()
        }
    )
    Column(
        modifier = Modifier
            .verticalScroll((rememberScrollState()))
            .height(800.dp)
    ) {
        TopAppBar(
            modifier = Modifier
                .height(60.dp),
            backgroundColor = Color(231,79,80),
            contentColor = Color.Black,
    ) {
        TextField(
            value = search,
            onValueChange = { search = it },
            label = {
                Text(
                    text = "Search",
                    color = Color.White
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color.White

            ),textStyle = LocalTextStyle.current.copy(color = Color.White),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        )
    }


        val filteredList = searchViewModel.searchList.filter {
            it.title.contains(search, ignoreCase = true)
        }

        // Show anime list only if there is no error message from view model
        if (searchViewModel.errorMessage.isEmpty()) {
            SearchList(list = filteredList) { id, title, image, bahan, desc ->
                Log.d("ClickItem", "this is id: $id")
                navController.navigate("Detail?id=$id?title=$title?image=$image?bahan=$bahan?desc=$desc")
            }
        } else {
            Log.e("AVM", "Something happened")
        }
    }

}

@Composable
fun SearchList(list: List<Model>, itemClick: (index: Int, title: String, imgUrl: String, bahan: String, Deskripsi:String)-> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
    ) {
        LazyColumn(modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, end = 20.dp)
            .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(list) { index, item ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(5.dp)
                    .shadow(elevation = 3.dp)
                    .clickable {
                        itemClick(item.id, item.title, item.image, item.bahan, item.desc)
                    },
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(8.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = item.image,
                                builder = {
//                                    scale(Scale.FILL)
//                                    placeholder(R.drawable.notification_action_background)
//                                    transformations(CircleCropTransformation())
                                }
                            ),
                            contentDescription = item.desc,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(120.dp)
                        )
                        Column(modifier = Modifier
                            .padding(4.dp)
                            .fillMaxHeight(),
//                            .weight(0.8f),
                        ) {
//                            Text(text = item.title)
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = R.string.ingredient),
                                style = MaterialTheme.typography.body1,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = item.bahan,
                                style = MaterialTheme.typography.body1,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VList(list: List<Model>, itemClick: (index: Int, title: String, imgUrl: String, bahan: String, Deskripsi:String)-> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
    ) {
        LazyColumn(modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, end = 20.dp)
            .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(list) { index, item ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(5.dp)
                    .shadow(elevation = 3.dp)
                    .clickable {
                        itemClick(item.id, item.title, item.image, item.bahan, item.desc)
                    },
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(8.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = item.image,
                                builder = {
//                                    scale(Scale.FILL)
//                                    placeholder(R.drawable.notification_action_background)
//                                    transformations(CircleCropTransformation())
                                }
                            ),
                            contentDescription = item.desc,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(120.dp)
                        )
                        Column(modifier = Modifier
                            .padding(4.dp)
                            .fillMaxHeight(),
//                            .weight(0.8f),
                        ) {
//                            Text(text = item.title)
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = R.string.ingredient),
                                style = MaterialTheme.typography.body1,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = item.bahan,
                                style = MaterialTheme.typography.body1,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )
        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}
