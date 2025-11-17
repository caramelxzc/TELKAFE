package com.candra.telkafers

import android.content.Intent // Import untuk perpindahan halaman
import android.os.Bundle
import android.widget.Button // Import untuk Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Kode bawaan untuk mengatur padding layar penuh (Edge to Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Inisialisasi Tombol dari Layout XML
        // ID 'button' adalah tombol Create Account
        val btnCreateAccount = findViewById<Button>(R.id.button)

        // ID 'button2' adalah tombol Log In
        val btnLogin = findViewById<Button>(R.id.button2)

        // 2. Logika Tombol Create Account -> Pindah ke CreateActivity
        btnCreateAccount.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        // 3. Logika Tombol Log In -> Pindah ke LoginActivity
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}