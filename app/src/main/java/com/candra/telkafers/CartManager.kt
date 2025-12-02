// CartManager.kt
package com.candra.telkafers

object CartManager {

    // Menggunakan MutableList agar item bisa ditambahkan, diubah, dan dihapus
    val cartItems = mutableListOf<CartItem>()

    /**
     * Menambahkan atau memperbarui item di keranjang dari Food item.
     */
    fun addItem(food: Food) {
        // Cek berdasarkan foodId (ID unik item makanan)
        val existingItem = cartItems.find { it.foodId == food.id }

        // Catatan: Asumsi food.id adalah unique ID untuk makanan

        if (existingItem != null) {
            // Item sudah ada, tambahkan kuantitasnya
            val newQuantity = existingItem.quantity + 1
            val index = cartItems.indexOf(existingItem)

            // Perbarui item lama dengan fungsi copy()
            val updatedItem = existingItem.copy(quantity = newQuantity)
            cartItems[index] = updatedItem

        } else {
            // Item belum ada, tambahkan item baru dengan kuantitas 1
            val newItem = CartItem(
                id = food.id,
                foodId = food.id,              // ⬅️ PERBAIKAN: foodId ditambahkan
                foodName = food.name,
                basePrice = food.price,
                quantity = 1,
                imageResourceId = food.imageUrl // ⬅️ PERBAIKAN: Mengambil ID drawable dari food.imageUrl
            )
            cartItems.add(newItem)
        }
    }

    /**
     * Mengosongkan keranjang (dipanggil saat checkout).
     */
    fun clearCart() {
        cartItems.clear()
    }
}