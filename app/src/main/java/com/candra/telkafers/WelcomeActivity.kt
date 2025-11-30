package com.candra.telkafers

import android.content.Intent
import android.os.Bundle
import android.os.Handler // Import Handler
import android.os.Looper // Import Looper
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    // Definisikan waktu tunda dalam milidetik (1000 ms = 1 detik)
    private val DELAY_TIME: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menampilkan layout activity_welcome (yang sudah Anda desain)
        setContentView(R.layout.activity_welcome)

        // Menggunakan Handler untuk menunda eksekusi kode
        Handler(Looper.getMainLooper()).postDelayed({
            // Kode di dalam blok ini akan dijalankan setelah DELAY_TIME

            // 1. Buat Intent untuk berpindah ke HomeActivity
            val intent = Intent(this, HomeActivity::class.java)

            // 2. Mulai Activity baru
            startActivity(intent)

            // 3. Tutup WelcomeActivity agar pengguna tidak bisa kembali ke halaman ini
            finish()

        }, DELAY_TIME) // Menerapkan waktu tunda (1000 ms)
    }
}