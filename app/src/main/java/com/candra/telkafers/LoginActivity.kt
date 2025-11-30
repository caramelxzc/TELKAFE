package com.candra.telkafers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // Kunci SharedPreferences WAJIB SAMA DI SEMUA ACTIVITY
    private val USERNAME_KEY = "USERNAME_KEY"
    private val PREFS_NAME = "UserPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Inisialisasi View
        val btnBack = findViewById<TextView>(R.id.btnBack)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val cbRemember = findViewById<CheckBox>(R.id.cbRemember)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvCreateAccount = findViewById<TextView>(R.id.tvCreateAccount)

        // 2. Aksi Tombol Log In
        btnLogin.setOnClickListener {
            val inputUsername = etEmail.text.toString().trim()
            val inputPassword = etPassword.text.toString().trim()

            // Cek Input Kosong
            if (inputUsername.isEmpty() || inputPassword.isEmpty()){
                Toast.makeText(this, "Mohon isi Email/Username dan Password", Toast.LENGTH_SHORT).show()
            } else {
                // Logika Sederhana (Diasumsikan login berhasil)

                // --- SIMPAN NAMA PENGGUNA KE SharedPreferences ---
                val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                with (sharedPref.edit()) {
                    // Simpan username yang berhasil login
                    putString(USERNAME_KEY, inputUsername)
                    apply()
                }

                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()

                // PINDAH KE HOME PAGE
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // ... Aksi Tombol Back, Create Account, dan Forgot Password ...
        btnBack.setOnClickListener { finish() }
        tvCreateAccount.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Lupa kata sandi belum diimplementasi.", Toast.LENGTH_SHORT).show()
        }
    }
}