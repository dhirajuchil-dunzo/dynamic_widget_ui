package com.example.dynamiclayouts.ui.composables

import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewWidget(url: String) {
    if (url.isEmpty() || url == "null")
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text("Invalid url")
        }
    else
        AndroidView(factory = {
            WebView(it).apply {
                loadUrl(url)
            }
        })
}