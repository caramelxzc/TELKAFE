package com.candra.telkafers

import android.content.Intent // Import Intent untuk pindah halaman
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Inisialisasi View
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin) // Tombol Log In
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvCreateAccount = findViewById<TextView>(R.id.tvCreateAccount)
        val rbRemember = findViewById<RadioButton>(R.id.rbRemember)

        // 2. Aksi Tombol Login -> Pindah ke WelcomeActivity
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Login Berhasil
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                // PINDAH KE WELCOME PAGE
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish() // Menutup halaman login agar tidak bisa kembali
            } else {
                Toast.makeText(this, "Mohon isi Username dan Password", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Aksi Tombol Back
        btnBack.setOnClickListener { finish() }

        // 4. Aksi Text Create Account (Pindah ke Halaman Daftar)
        tvCreateAccount.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        // 5. Logika Remember Me
        rbRemember.setOnClickListener {
            if (rbRemember.isSelected) {
                rbRemember.isChecked = false
                rbRemember.isSelected = false
            } else {
                rbRemember.isChecked = true
                rbRemember.isSelected = true
            }
        }

        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Fitur belum tersedia", Toast.LENGTH_SHORT).show()
        }
    }
}