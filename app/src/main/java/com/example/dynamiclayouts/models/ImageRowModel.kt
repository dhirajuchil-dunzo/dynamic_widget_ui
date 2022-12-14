package com.example.dynamiclayouts.models

data class ImageRowModel(var list: List<ImageRowItem>): BaseWidgetModel()


data class ImageRowItem(var url: String, var label: String) : BaseWidgetModel()
