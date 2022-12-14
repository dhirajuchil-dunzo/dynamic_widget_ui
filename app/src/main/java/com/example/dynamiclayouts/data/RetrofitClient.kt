package com.example.dynamiclayouts.data

import com.example.dynamiclayouts.extra.RuntimeTypeAdapterFactory
import com.example.dynamiclayouts.models.*
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getAPI(): LayoutAPI {
        var mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        var mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()
        val baseModelAdapterFactory: RuntimeTypeAdapterFactory<BaseWidgetModel> =
            RuntimeTypeAdapterFactory
                .of(BaseWidgetModel::class.java, "widgetType", true)
                .registerSubtype(GreetingModel::class.java, "GREETING")
                .registerSubtype(ImageWithTextModel::class.java, "IMAGE_TEXT")
                .registerSubtype(ImageRowModel::class.java, "IMAGE_ROW")
        val baseActionAdapterFactory: RuntimeTypeAdapterFactory<BaseAction> =
            RuntimeTypeAdapterFactory
                .of(BaseAction::class.java, "actionType", true)
                .registerSubtype(NavigateAction::class.java, "NAVIGATE")

        var gson = GsonBuilder().registerTypeAdapterFactory(baseModelAdapterFactory)
            .registerTypeAdapterFactory(baseActionAdapterFactory).create()

        var retrofit: Retrofit = retrofit2.Retrofit.Builder()
            .baseUrl("https://dynamic-layouts-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(mOkHttpClient)
            .build()

        return retrofit.create(LayoutAPI::class.java)
    }

}