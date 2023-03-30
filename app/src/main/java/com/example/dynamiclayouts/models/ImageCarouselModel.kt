package com.example.dynamiclayouts.models

data class ImageCarouselModel(var list: List<ImageCarouselItem>): BaseWidgetModel()


data class ImageCarouselItem(var url: String, var label: String) : BaseWidgetModel()
