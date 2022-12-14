package com.example.dynamiclayouts.ui.composables


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.dynamiclayouts.R
import com.example.dynamiclayouts.models.ImageWithTextModel
import com.example.dynamiclayouts.utils.ActionManager

@Composable
fun ImageWithTextWidget(imageWithTextModel: ImageWithTextModel, navController: NavController) {
    val fontColor = imageWithTextModel.text?.fontColor
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp,
        modifier = Modifier
            .padding(12.dp)
            .clickable {
                ActionManager.executeAction(imageWithTextModel.action, navController, null)
            }) {
        Column() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(R.drawable.loading)
                    .crossfade(true).scale(Scale.FILL)
                    .data(imageWithTextModel.imageUrl).build(),
                contentDescription = null,

                modifier = Modifier.fillMaxWidth(),

                )
            Text(
                text = imageWithTextModel.text?.text!!,
                style = TextStyle(
                    color = Color(
                        fontColor?.red ?: 0,
                        fontColor?.blue ?: 0,
                        fontColor?.green ?: 0
                    )
                ),
                modifier = Modifier.padding(16.dp)
            )

        }

    }
}