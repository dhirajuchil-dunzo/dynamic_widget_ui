package com.example.dynamiclayouts.data

import com.example.dynamiclayouts.models.LayoutModel
import retrofit2.http.GET

interface LayoutAPI {
    @GET("layouts.json")
    suspend fun getLayout(): Map<String,LayoutModel>
}