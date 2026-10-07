package com.featuredapps.anasgdxj.data

import android.content.Context
import com.featuredapps.anasgdxj.api.ApiService
import com.featuredapps.anasgdxj.db.AppDatabase
import com.featuredapps.anasgdxj.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostRepository(private val context: Context) {

    private val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private val db by lazy { AppDatabase.getInstance(context) }

    suspend fun getPosts(forceRefresh: Boolean = false): List<Post> = withContext(Dispatchers.IO) {
        val local = db.postDao().getAll()
        if (local.isNotEmpty() && !forceRefresh) {
            return@withContext local
        }

        // fetch remote
        val remote = api.getPosts()
        db.postDao().clear()
        db.postDao().insertAll(remote)
        return@withContext remote
    }
}
