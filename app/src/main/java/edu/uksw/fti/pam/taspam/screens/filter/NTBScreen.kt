package edu.uksw.fti.pam.taspam.screens.filter

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.uksw.fti.pam.taspam.ButtonNavItem
import edu.uksw.fti.pam.taspam.model.SearchViewModel
import edu.uksw.fti.pam.taspam.screens.SearchList
import edu.uksw.fti.pam.taspam.R
import edu.uksw.fti.pam.taspam.model.NTBViewModel

@Composable
fun NTBScreen(
    viewModel: NTBViewModel,
    navController: NavController
) {
    LaunchedEffect(
        Unit,
        block = {
            viewModel.getList()
        }
    )
    Column (modifier = Modifier
        .verticalScroll((rememberScrollState()))
        .height(1400.dp)
    ){
        TopAppBar(
            modifier = Modifier.height(60.dp),
            backgroundColor = Color(231,79,80),
            contentColor = Color.Black,
        ){
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable { navController.navigate(route = ButtonNavItem.Filter.screen_route) }
                        .padding(start = 10.dp, end = 20.dp),
                    Color.White
                )
                Text(
                    text = stringResource(id = R.string.ntb),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
        when {
            viewModel.errorMessage.isEmpty() -> {
                SearchList(list = viewModel.List) { id, title, image, bahan, desc ->
                    Log.d("ClickItem", "this is id: $id")
                    navController.navigate("Detail?id=$id?title=$title?image=$image?bahan=$bahan?desc=$desc")
                }
            }
            else -> Log.e("AVM", "Something happened")
        }
    }
}
