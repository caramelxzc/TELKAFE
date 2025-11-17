package com.candra.telkafers

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 1. Inisialisasi View
        val btnNotification = findViewById<ImageView>(R.id.btnNotification)
        val btnCart = findViewById<ImageView>(R.id.btnCart)
        val tvHiUser = findViewById<TextView>(R.id.tvHiUser)

        // Contoh: Jika nanti ingin mengganti nama user secara dinamis
        // val username = intent.getStringExtra("USERNAME") ?: "Telkafér"
        // tvHiUser.text = "Hi, $username!"

        // 2. Aksi Klik Notifikasi
        btnNotification.setOnClickListener {
            Toast.makeText(this, "Tidak ada notifikasi baru", Toast.LENGTH_SHORT).show()
        }

        // 3. Aksi Klik Cart
        btnCart.setOnClickListener {
            Toast.makeText(this, "Keranjang belanja kosong", Toast.LENGTH_SHORT).show()
        }
    }
}