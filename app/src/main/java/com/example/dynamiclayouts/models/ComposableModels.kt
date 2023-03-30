package com.example.dynamiclayouts.models


enum class WIDGET_TYPE {
    GREETING, IMAGE_TEXT, IMAGE_CAROUSEL,
}


open class BaseWidgetModel() {
    var widgetType: WIDGET_TYPE? = null
    var action: BaseAction? = null
    var padding: Int? = null

    override fun toString(): String {
        return "BaseWidgetModel(widgetType=$widgetType, action=$action, padding=$padding)"
    }


}


data class ImageWithTextModel(
    var imageUrl: String? = null,
    var text: TextModel? = null
) : BaseWidgetModel()


data class GreetingModel(
    var name: TextModel? = null,
    var text: TextModel? = null
) : BaseWidgetModel()

