package com.featuredapps.anasgdxj

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.featuredapps.anasgdxj.databinding.ActivityPostsBinding

class PostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding
    private val viewModel: PostsViewModel by viewModels { PostsViewModel.Factory(application) }
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PostAdapter { post ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("post_id", post.id)
            intent.putExtra("post_title", post.title)
            intent.putExtra("post_body", post.body)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.posts.observe(this) { posts ->
            adapter.submitList(posts)
            binding.swipeRefresh.isRefreshing = false
        }

        viewModel.loading.observe(this) { loading ->
            binding.progressBar.visibility = if (loading) android.view.View.VISIBLE else android.view.View.GONE
        }

        viewModel.error.observe(this) { err ->
            if (err != null) {
                // simple toast
                android.widget.Toast.makeText(this, err, android.widget.Toast.LENGTH_SHORT).show()
            }
        }

        binding.logoutButton.setOnClickListener {
            getSharedPreferences("anas_prefs", MODE_PRIVATE).edit().remove("username").apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // initial load
        viewModel.loadPosts()
    }
}
