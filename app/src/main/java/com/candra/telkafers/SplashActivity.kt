package com.candra.telkafers

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Menunda selama 3000ms (3 detik)
        Handler(Looper.getMainLooper()).postDelayed({

            // Pindah ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Tutup SplashActivity agar tidak bisa kembali ke logo saat tombol back ditekan
            finish()

        }, 1500) // 2000 = 2 detik
    }
}