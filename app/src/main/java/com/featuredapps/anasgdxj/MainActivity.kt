package com.featuredapps.anasgdxj

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.featuredapps.anasgdxj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleText.text = getString(R.string.app_title)
        binding.subtitleText.text = getString(R.string.app_subtitle)

        binding.downloadButton.setOnClickListener {
            val url = "https://github.com/Featuredapps/anas_gdxj.apk"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }
}
