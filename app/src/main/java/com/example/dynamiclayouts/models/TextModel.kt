package com.example.dynamiclayouts.models

import androidx.compose.ui.text.font.FontWeight
import com.example.dynamiclayouts.models.style.FontColor

enum class FONT_WEIGHT {
    BOLD, NORMAL
}
fun getFontWeight(fontWeight: FONT_WEIGHT?): FontWeight {
    return when (fontWeight) {
        FONT_WEIGHT.BOLD -> FontWeight.Bold
        FONT_WEIGHT.NORMAL -> FontWeight.Normal
        else -> FontWeight.Normal
    }
}


data class TextModel (
    var text: String? = null,
    var fontSize: Int? = null,
    var fontColor: FontColor? = null,
    var fontWeight: FONT_WEIGHT = FONT_WEIGHT.NORMAL)