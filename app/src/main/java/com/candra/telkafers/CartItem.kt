// CartItem.kt

package com.candra.telkafers

data class CartItem(
    val id: Int,
    val foodId: Int,             // ⬅️ Diperlukan untuk pelacakan unik item makanan
    val foodName: String,
    val basePrice: Int,
    var quantity: Int,           // Kuantitas harus 'var' agar bisa diubah
    val imageResourceId: Int     // ⬅️ Hanya gunakan satu properti untuk ID resource gambar
)