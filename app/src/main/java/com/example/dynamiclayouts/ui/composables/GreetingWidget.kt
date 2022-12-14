package com.example.dynamiclayouts.ui.composables


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.dynamiclayouts.models.GreetingModel
import com.example.dynamiclayouts.models.getFontWeight


@OptIn(ExperimentalUnitApi::class)
@Composable
fun GreetingWidget(greetingModel: GreetingModel, navController: NavController) {
    val nameFontCOlor = greetingModel.name?.fontColor
    val textFontColor = greetingModel.text?.fontColor
    Card(
        modifier = Modifier.padding(greetingModel.padding?.dp ?: 16.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(
                text = "Hi, ${greetingModel.name?.text!!}",
                style = TextStyle(
                    fontSize = greetingModel.name?.fontSize?.sp ?: 20.sp,
                    fontWeight = getFontWeight(greetingModel.name?.fontWeight),
                    color = Color(
                        nameFontCOlor?.red ?: 0,
                        nameFontCOlor?.green ?: 0,
                        nameFontCOlor?.blue ?: 0
                    )
                )
            )
            Text(
                text = greetingModel.text?.text ?: "",
                style = TextStyle(
                    fontSize = greetingModel.text?.fontSize?.sp ?: 14.sp,
                    fontWeight = getFontWeight(greetingModel.text?.fontWeight),
                    color = Color(
                        textFontColor?.red ?: 0,
                        textFontColor?.green ?: 0,
                        textFontColor?.blue ?: 0
                    )
                )
            )
        }

    }
}


