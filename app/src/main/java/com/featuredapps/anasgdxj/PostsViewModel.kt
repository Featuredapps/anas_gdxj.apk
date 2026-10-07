package com.featuredapps.anasgdxj

import android.app.Application
import androidx.lifecycle.*
import com.featuredapps.anasgdxj.data.PostRepository
import com.featuredapps.anasgdxj.model.Post
import kotlinx.coroutines.launch

class PostsViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = PostRepository(application.applicationContext)

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun loadPosts() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val list = repo.getPosts(false)
                _posts.value = list
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "حدث خطأ"
            } finally {
                _loading.value = false
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val list = repo.getPosts(true)
                _posts.value = list
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "حدث خطأ"
            } finally {
                _loading.value = false
            }
        }
    }

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PostsViewModel(app) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
