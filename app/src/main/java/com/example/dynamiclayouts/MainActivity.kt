package com.example.dynamiclayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dynamiclayouts.models.*
import com.example.dynamiclayouts.ui.composables.GreetingWidget
import com.example.dynamiclayouts.ui.composables.ImageWithTextWidget
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
    NavHost(navController = navController, startDestination = "home_layout", builder = {
        composable(route = "home_layout") {
            BaseWidget(layoutId = "home_layout", navController)
        }
        composable(route = "second_screen") {
            SecondScreen()
        }
    })
}

@Composable
fun BaseWidget(layoutId: String, navController: NavController) {
    var mainViewModel: MainViewModel = viewModel()
    mainViewModel.getLayout(layoutId)
    val layout = mainViewModel.state.value
    return LazyColumn {
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
        WIDGET_TYPE.IMAGE_ROW -> ImageRowWidget(
            imageRowModel = it as ImageRowModel,
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
