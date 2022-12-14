package com.example.dynamiclayouts.models

enum class ACTION_TYPE {
    NAVIGATE, BOTTOM_SHEET, WEB, CUSTOM
}


open class BaseAction() {
    var actionType: ACTION_TYPE? = null

    override fun toString(): String {
        return "BaseAction(actionType=$actionType)"
    }
}


data class NavigateAction(var endpoint: String) : BaseAction()