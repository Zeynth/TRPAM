package edu.uksw.fti.pam.taspam.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import edu.uksw.fti.pam.taspam.R
import coil.size.Scale

@Composable
fun DetailScreen(id: String?, title: String?, image: String?, bahan: String?, desc: String? ) {

    TopAppBar(modifier=Modifier.height(60.dp),
        backgroundColor = Color(231,79,80),
        contentColor = Color.Black,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.
            fillMaxWidth()
        ) {
            Text(
                text = "$title",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
    ) {
        Column(

            modifier = Modifier
                .verticalScroll((rememberScrollState()))
                .height(1100.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "$image",
                    builder = {
                        scale(Scale.FILL)
                    }
                ),
                contentDescription = "$desc",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxSize()
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(id = R.string.ingredient),
                textAlign = TextAlign.Left
            )

            Text(
                text = "$bahan",
                style = MaterialTheme.typography.body1,
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.make),
                style = MaterialTheme.typography.body1,
            )

            Text(
                text = "$desc",
                style = MaterialTheme.typography.body1,
            )
        }
    }
}