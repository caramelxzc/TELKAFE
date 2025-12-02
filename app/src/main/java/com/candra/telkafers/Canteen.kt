package com.candra.telkafers

data class Canteen(
    val name: String,
    val description: String,
    val imageUrl: Int, // Menggunakan Int untuk ID resource drawable lokal
    val isOpen: Boolean
)