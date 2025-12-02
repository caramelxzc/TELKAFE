// Food.kt
package com.candra.telkafers

data class Food(
    val id: Int,        // ⬅️ PERBAIKAN KRITIS 1: Diperlukan untuk CartManager (menambah kuantitas)
    val name: String,
    val price: Int,     // ⬅️ PERBAIKAN KRITIS 2: Harus Int agar bisa dihitung
    val imageUrl: Int   // Menggunakan imageUrl (Int) untuk konsistensi dengan Adapter/CartManager
)