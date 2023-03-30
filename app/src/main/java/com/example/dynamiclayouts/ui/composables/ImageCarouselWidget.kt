package com.example.dynamiclayouts.ui.composables

import android.widget.Toast
import androidx.compose.foundation.clickable
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
import com.example.dynamiclayouts.models.ImageCarouselItem
import com.example.dynamiclayouts.models.ImageCarouselModel
import com.example.dynamiclayouts.utils.ActionManager

@Composable
fun ImageCarouselWidget(imageCarouselModel: ImageCarouselModel, navController: NavController) {
    val current = LocalContext.current
    LazyRow(contentPadding = PaddingValues(12.dp)) {
        items(imageCarouselModel.list) {
            Box(modifier = Modifier.clickable {
                ActionManager.executeAction(imageCarouselModel.action, navController) {
                    Toast.makeText(
                        current,
                        "${it.label} clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                ImageCarouselItemWidget(
                    imageCarouselItem = it,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun ImageCarouselItemWidget(imageCarouselItem: ImageCarouselItem, navController: NavController) {

    Card(modifier = Modifier
        .padding(8.dp)
        , shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(R.drawable.loading)
                    .crossfade(true).scale(Scale.FILL).transformations(CircleCropTransformation())
                    .data(imageCarouselItem.url).build(),
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .height(120.dp),
                )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                imageCarouselItem.label,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }

}