package com.featuredapps.anasgdxj

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.featuredapps.anasgdxj.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Simple demo login — accept any non-empty username
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEdit.text.toString().trim()
            if (username.isNotEmpty()) {
                // save username in prefs
                val prefs = getSharedPreferences("anas_prefs", MODE_PRIVATE)
                prefs.edit().putString("username", username).apply()

                startActivity(Intent(this, PostsActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "أدخل اسم مستخدم", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
