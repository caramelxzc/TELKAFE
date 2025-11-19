package com.candra.telkafers

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    // Variabel untuk Kategori
    private lateinit var rvCategories: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryList: ArrayList<Category>

    // Variabel untuk Menu Makanan
    private lateinit var rvFoodMenu: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var foodList: ArrayList<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // --- 1. SETUP KATEGORI ---
        rvCategories = findViewById(R.id.rvCategories)
        rvCategories.setHasFixedSize(true)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        categoryList = ArrayList()
        categoryList.add(Category("All", true)) // Default terpilih
        categoryList.add(Category("Coffee"))
        categoryList.add(Category("Non-Coffee"))
        categoryList.add(Category("Tea"))
        categoryList.add(Category("Snacks"))
        categoryList.add(Category("Dessert"))

        categoryAdapter = CategoryAdapter(categoryList)
        rvCategories.adapter = categoryAdapter

        // --- 2. SETUP MENU MAKANAN (GRID) ---
        rvFoodMenu = findViewById(R.id.rvFoodMenu)
        rvFoodMenu.setHasFixedSize(true)
        // Menggunakan Grid 2 Kolom
        rvFoodMenu.layoutManager = GridLayoutManager(this, 2)

        foodList = ArrayList()
        // Menggunakan foto1, foto2, foto3 yang kamu punya
        foodList.add(Food("Ayam Geprek", "Rp 15.000", R.drawable.foto1))
        foodList.add(Food("Nasi Goreng", "Rp 12.000", R.drawable.foto2))
        foodList.add(Food("Mie Goreng", "Rp 10.000", R.drawable.foto3))
        foodList.add(Food("Es Teh Manis", "Rp 3.000", R.drawable.foto1))
        foodList.add(Food("Kopi Susu", "Rp 5.000", R.drawable.foto2))
        foodList.add(Food("Roti Bakar", "Rp 8.000", R.drawable.foto3))

        foodAdapter = FoodAdapter(foodList)
        rvFoodMenu.adapter = foodAdapter
    }
}