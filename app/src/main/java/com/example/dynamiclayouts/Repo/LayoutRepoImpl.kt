package com.example.dynamiclayouts.Repo

import android.util.Log
import com.example.dynamiclayouts.data.RetrofitClient
import com.example.dynamiclayouts.models.LayoutModel

class LayoutRepoImpl : LayoutRepo {
    companion object {
        private const val TAG = "LayoutRepo"
    }

    override suspend fun getLayout(layoutId: String): LayoutModel? {
        val response = RetrofitClient.getAPI().getLayout()
        Log.d(TAG, "getLayout: $response")
        return response[layoutId]
    }
}