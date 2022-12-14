package com.example.dynamiclayouts.Repo

import com.example.dynamiclayouts.models.LayoutModel

interface LayoutRepo {

    suspend fun getLayout(layoutId:String): LayoutModel?
}