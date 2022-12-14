package com.example.dynamiclayouts.models

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.dynamiclayouts.R

@Composable
fun ImageRowWidget(imageRowModel: ImageRowModel, navController: NavController) {
    LazyRow(contentPadding = PaddingValues(12.dp)) {
        items(imageRowModel.list) {
            ImageRowItemWidget(
                imageRowItem = it,
                navController = navController
            )
        }
    }
}

@Composable
fun ImageRowItemWidget(imageRowItem: ImageRowItem, navController: NavController) {
    Card(modifier =Modifier.padding(8.dp), shape = RoundedCornerShape(8.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(R.drawable.loading)
                    .crossfade(true).scale(Scale.FILL).transformations(CircleCropTransformation())
                    .data(imageRowItem.url).build(),
                contentDescription = null,

                modifier = Modifier
                    .width(200.dp)
                    .height(120.dp),

                )
            Spacer(modifier = Modifier.height(20.dp))
            Text(imageRowItem.label, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
    }

}