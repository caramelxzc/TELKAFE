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

class CreateActivity : AppCompatActivity() {

    // Kunci SharedPreferences
    private val USERNAME_KEY = "USERNAME_KEY"
    private val PREFS_NAME = "UserPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        // 1. Inisialisasi View (Sesuai ID XML Anda)
        val btnBack = findViewById<TextView>(R.id.btnBack)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)
        val btnCreateAccount = findViewById<Button>(R.id.btnCreateAccount)
        val tvLoginLink = findViewById<TextView>(R.id.tvLoginLink)

        // 2. Aksi Tombol Create Account
        btnCreateAccount.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            // Validasi Field & Terms
            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                Toast.makeText(this, "Mohon lengkapi semua data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != confirmPassword) {
                Toast.makeText(this, "Konfirmasi Password tidak cocok.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!cbTerms.isChecked) {
                Toast.makeText(this, "Anda harus menyetujui Syarat & Ketentuan.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // --- 3. SIMPAN NAMA PENGGUNA KE SharedPreferences ---
            val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                // Simpan username yang baru didaftarkan
                putString(USERNAME_KEY, username)
                apply()
            }

            // Pendaftaran Berhasil
            Toast.makeText(this, "Akun berhasil dibuat! Welcome, $username", Toast.LENGTH_SHORT).show()

            // --- 4. PINDAH LANGSUNG KE HOME ACTIVITY ---
            val intent = Intent(this, HomeActivity::class.java) // Pindah ke Home!
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // 5. Aksi Tombol Back
        btnBack.setOnClickListener { finish() }

        // 6. Aksi Text Log In (Kembali ke Halaman Login)
        tvLoginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}