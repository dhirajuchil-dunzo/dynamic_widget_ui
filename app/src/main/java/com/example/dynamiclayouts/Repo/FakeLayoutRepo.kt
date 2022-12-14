package com.example.dynamiclayouts

import com.example.dynamiclayouts.Repo.LayoutRepo
import com.example.dynamiclayouts.models.*
import com.example.dynamiclayouts.models.style.FontColor
import kotlinx.coroutines.delay

class FakeLayoutRepo: LayoutRepo {

    val layouts = hashMapOf<String, LayoutModel>(
        "home_layout" to LayoutModel(
            listOf(

                GreetingModel().apply {
                    widgetType = WIDGET_TYPE.GREETING
                    padding = 10
                    name = TextModel().apply {
                        text = "Dhiraj"
                        fontSize = 20
                        fontWeight = FONT_WEIGHT.BOLD
                    }
                    text = TextModel().apply {
                        text =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        fontSize = 14
                    }
                },
                ImageWithTextModel().apply {
                    widgetType = WIDGET_TYPE.IMAGE_TEXT
                    imageUrl = "https://placedog.net/1200"
                    action = NavigateAction("second_screen").apply {
                        actionType=ACTION_TYPE.NAVIGATE
                    }
                    text = TextModel().apply {
                        text =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Elit duis tristique sollicitudin nibh sit amet commodo nulla. Quis enim lobortis scelerisque fermentum dui faucibus in. Quis varius quam quisque id. Vulputate sapien nec sagittis aliquam malesuada bibendum."
                        fontSize = 14
                        fontColor = FontColor(0, 0, 0)
                    }

                },
                ImageRowModel(
                     list = listOf(
                        ImageRowItem("https://placedog.net/120?random", "Item 1"),
                        ImageRowItem("https://placedog.net/120?random", "Item 2"),
                        ImageRowItem("https://placedog.net/120?random", "Item 3"),
                        ImageRowItem("https://placedog.net/120?random", "Item 4"),
                        ImageRowItem("https://placedog.net/120?random", "Item 5"),
                        ImageRowItem("https://placedog.net/120?random", "Item 6"),
                        ImageRowItem("https://placedog.net/120?random.", "Item 7"),
                    )
                ).apply {
                    widgetType=WIDGET_TYPE.IMAGE_ROW
                }

            )
        )

    )

    suspend fun getAllFakeLayouts(): HashMap<String, LayoutModel> {
        delay(2000)
        return layouts
    }

     override suspend fun getLayout(layoutId: String): LayoutModel? {
        delay(2000)
        return layouts[layoutId] ?: LayoutModel()
    }

}