package com.candra.telkafers

data class CartItem(
    val name: String,
    val price: Double,
    val imageResId: Int,
    var quantity: Int
)