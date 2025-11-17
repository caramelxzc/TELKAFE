package com.candra.telkafers

import android.content.Intent // Import Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        // 1. Inisialisasi View
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val rbTerms = findViewById<RadioButton>(R.id.rbTerms)
        val btnCreateAccount = findViewById<Button>(R.id.btnCreateAccount) // Tombol Create Account
        val tvLoginLink = findViewById<TextView>(R.id.tvLoginLink)

        // 2. Aksi Tombol Create Account -> Pindah ke WelcomeActivity
        btnCreateAccount.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()
            val isTermsAccepted = rbTerms.isChecked

            // Validasi Input
            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else if (!isTermsAccepted) {
                Toast.makeText(this, "Please agree to Terms & Services", Toast.LENGTH_SHORT).show()
            } else {
                // Akun Berhasil Dibuat
                Toast.makeText(this, "Account Created! Welcome $username", Toast.LENGTH_SHORT).show()

                // PINDAH KE WELCOME PAGE
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish() // Menutup halaman register
            }
        }

        // 3. Aksi Tombol Back
        btnBack.setOnClickListener { finish() }

        // 4. Aksi Text Log In (Pindah ke Halaman Login)
        tvLoginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 5. Logika Terms Checkbox
        rbTerms.setOnClickListener {
            if (rbTerms.isSelected) {
                rbTerms.isChecked = false
                rbTerms.isSelected = false
            } else {
                rbTerms.isChecked = true
                rbTerms.isSelected = true
            }
        }
    }
}