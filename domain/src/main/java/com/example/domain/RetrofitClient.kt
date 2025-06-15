package com.example.domain

import com.example.domain.apiinterfaces.AlbumApi
import com.example.domain.apiinterfaces.PostApi
import com.example.domain.apiinterfaces.UserApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val postApi: PostApi = retrofit.create(PostApi::class.java)
    val albumsApi: AlbumApi = retrofit.create(AlbumApi::class.java)
    val userApi: UserApi = retrofit.create(UserApi::class.java)
}