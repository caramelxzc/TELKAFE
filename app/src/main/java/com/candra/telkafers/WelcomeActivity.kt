package com.candra.telkafers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // 1. Hubungkan variabel dengan ID tombol di XML
        val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)

        // 2. Beri aksi ketika tombol diklik
        btnGetStarted.setOnClickListener {

            // Pindah ke HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            // Tutup halaman Welcome agar user tidak balik lagi ke sini
            finish()
        }
    }
}