package com.candra.telkafers

data class Category(
    val name: String,
    var isSelected: Boolean = false,
    val iconResId: Int = 0 // WAJIB ADA untuk menampung R.drawable.ikon
)