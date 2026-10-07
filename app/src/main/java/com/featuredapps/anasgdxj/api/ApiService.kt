package com.featuredapps.anasgdxj.api

import com.featuredapps.anasgdxj.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}
