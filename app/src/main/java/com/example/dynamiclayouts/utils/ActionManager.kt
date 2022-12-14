package com.example.dynamiclayouts.utils

import androidx.navigation.NavController
import com.example.dynamiclayouts.models.ACTION_TYPE
import com.example.dynamiclayouts.models.BaseAction
import com.example.dynamiclayouts.models.NavigateAction

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
                ACTION_TYPE.WEB -> TODO()
                ACTION_TYPE.CUSTOM -> TODO()
                null -> TODO()
            }
        }

    }
}