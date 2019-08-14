package com.purna.unsplashdatasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.purna.unsplashdatasource.services.UnSplashListServices
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class UnSplashRetrofitProvider(private val okHttpClient: OkHttpClient) {
    private val unsplashOkHttpClient by lazy {
        okHttpClient.newBuilder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

    private val unSplashRetrofit: Retrofit by lazy {
        val json = Json(JsonConfiguration(strictMode = false))
        Retrofit.Builder()
            .client(unsplashOkHttpClient)
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("https://api.unsplash.com/")
            .build()
    }

    val unsplashImageListService by lazy {
        unSplashRetrofit.create(UnSplashListServices::class.java)
    }
}