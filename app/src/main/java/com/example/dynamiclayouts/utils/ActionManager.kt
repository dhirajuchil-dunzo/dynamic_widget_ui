package com.example.dynamiclayouts.utils

import androidx.navigation.NavController
import com.example.dynamiclayouts.models.ACTION_TYPE
import com.example.dynamiclayouts.models.BaseAction
import com.example.dynamiclayouts.models.NavigateAction
import com.example.dynamiclayouts.models.OpenUrl

object ActionManager {
    fun executeAction(
        baseAction: BaseAction?,
        navController: NavController,
        callback: (() -> Unit)?
    ) {
        baseAction?.let {
            when (baseAction.actionType) {
                ACTION_TYPE.NAVIGATE -> navController.navigate((baseAction as NavigateAction).endpoint)
                ACTION_TYPE.BOTTOM_SHEET -> TODO()
                ACTION_TYPE.OPEN_URL -> navController.navigate("open_url?url=${(baseAction as OpenUrl).url}")
                ACTION_TYPE.CUSTOM -> callback?.invoke()
                null -> TODO()
            }
        }

    }
}