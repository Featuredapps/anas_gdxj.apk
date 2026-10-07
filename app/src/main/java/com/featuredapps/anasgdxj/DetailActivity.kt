package com.featuredapps.anasgdxj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.featuredapps.anasgdxj.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("post_title")
        val body = intent.getStringExtra("post_body")

        binding.titleText.text = title
        binding.bodyText.text = body
    }
}
