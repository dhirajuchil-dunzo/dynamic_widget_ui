package com.example.dynamiclayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dynamiclayouts.models.*
import com.example.dynamiclayouts.ui.composables.GreetingWidget
import com.example.dynamiclayouts.ui.composables.ImageCarouselWidget
import com.example.dynamiclayouts.ui.composables.ImageWithTextWidget
import com.example.dynamiclayouts.ui.composables.WebViewWidget
import com.example.dynamiclayouts.ui.screens.SecondScreen
import com.example.dynamiclayouts.ui.theme.DynamicLayoutsTheme
import com.example.dynamiclayouts.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //LayoutRepo().writeToFirebase()
        setContent {
            DynamicLayoutsTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dynamic_layout") {
        composable(route = "dynamic_layout", arguments = listOf(navArgument("layoutId") {
            type = NavType.StringType
            defaultValue = "home_layout"
            nullable = false
        })) {
            BaseWidget(
                layoutId = it.arguments?.getString("layoutId") ?: "home_layout",
                navController
            )
        }
        composable(route = "second_screen") {
            SecondScreen()
        }
        composable(route = "open_url?url={url}", arguments = listOf(navArgument("url") {
            type = NavType.StringType
        })) {
            WebViewWidget(it.arguments?.getString("url") ?: "")
        }
    }
}

@Composable
fun BaseWidget(layoutId: String, navController: NavController) {
    var mainViewModel: MainViewModel = viewModel()
    LaunchedEffect(key1 = layoutId) {
        mainViewModel.getLayout(layoutId)
    }
    val layout = mainViewModel.state.value
    LazyColumn(modifier = Modifier.background(Color.LightGray)) {
        items(layout.widgets) {
            BuildWidgets(it, navController)
        }
    }
}

@Composable
private fun BuildWidgets(it: BaseWidgetModel, navController: NavController) {
    when (it.widgetType) {
        WIDGET_TYPE.GREETING -> GreetingWidget(greetingModel = it as GreetingModel, navController)
        WIDGET_TYPE.IMAGE_TEXT -> ImageWithTextWidget(it as ImageWithTextModel, navController)
        WIDGET_TYPE.IMAGE_CAROUSEL -> ImageCarouselWidget(
            imageCarouselModel = it as ImageCarouselModel,
            navController = navController
        )
        else -> {}
    }
}


/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DynamicLayoutsTheme {
        MainApp()
    }
}*/
