// CartManager.kt
package com.candra.telkafers

object CartManager {

    // Menggunakan MutableList agar item bisa ditambahkan, diubah, dan dihapus
    val cartItems = mutableListOf<CartItem>()

    /**
     * Menambahkan atau memperbarui item di keranjang dari Food item.
     */
    fun addItem(food: Food) {
        val existingItem = cartItems.find { it.id == food.id }

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
                foodName = food.name,
                basePrice = food.price,
                quantity = 1,
                imageUrl = food.imageUrl
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