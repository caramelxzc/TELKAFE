package com.candra.telkafers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

// PASTIKAN ANDA SUDAH MEMILIKI: GridSpacingItemDecoration.kt, Food.kt, dll.

class HomeActivity : AppCompatActivity() {

    private val USERNAME_KEY = "USERNAME_KEY"
    private val PREFS_NAME = "UserPref"

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryList: ArrayList<Category>

    private lateinit var rvMostPopular: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var foodList: ArrayList<Food>

    private lateinit var tvGreeting: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // --- 0. AMBIL NAMA PENGGUNA DARI LOKAL (SharedPreferences) ---
        tvGreeting = findViewById(R.id.tvGreeting)
        loadUserName()

        // --- 1. SETUP HEADER & ACTION ---
        val imgCart = findViewById<ImageView>(R.id.imgCart)
        val btnBannerOrder = findViewById<Button>(R.id.btnBannerOrder)

        imgCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        btnBannerOrder.setOnClickListener {
            Toast.makeText(this, "Ayam Geprek ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show()
        }

        // --- 2. SETUP KATEGORI (HORIZONTAL LIST) ---
        rvCategories = findViewById(R.id.rvCategories)
        rvCategories.setHasFixedSize(true)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        categoryList = ArrayList()
        categoryList.add(Category("Drinks", false, R.drawable.cafe))
        categoryList.add(Category("Main Course", false, R.drawable.ricebowl))
        categoryList.add(Category("Snacks", false, R.drawable.fries))
        categoryList.add(Category("Desserts", true, R.drawable.doughnut))

        categoryAdapter = CategoryAdapter(categoryList)
        rvCategories.adapter = categoryAdapter

        // --- 3. SETUP MENU MAKANAN POPULER (GRID 2 KOLOM) ---
        rvMostPopular = findViewById(R.id.rvMostPopular)

        // PERBAIKAN KRUSIAL: setHasFixedSize(false) untuk memperbolehkan perhitungan tinggi dinamis
        rvMostPopular.setHasFixedSize(false)

        // --- IMPLEMENTASI ITEM DECORATION ---
        val spacingInDp = 16
        val spacingInPixels = (spacingInDp * resources.displayMetrics.density).toInt()

        val gridLayoutManager = GridLayoutManager(this, 2)
        rvMostPopular.layoutManager = gridLayoutManager

        rvMostPopular.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount = 2,
                spacing = spacingInPixels,
                includeEdge = false
            )
        )
        // --- END: IMPLEMENTASI ITEM DECORATION ---

        // =================================================================
        // PERBAIKAN UTAMA: INISIALISASI foodList dan FoodAdapter HANYA DI SINI
        // =================================================================

        // 3.1 Isi data foodList
        foodList = ArrayList()
        foodList.add(Food("Ayam Geprek", "Rp 10.000", R.drawable.ayamgeprek))
        foodList.add(Food("Es Teh", "Rp 3.000", R.drawable.esteh))
        foodList.add(Food("Nescafe", "Rp 8.000", R.drawable.nescafe))
        foodList.add(Food("Pop Mie", "Rp 5.000", R.drawable.popmie))
        foodList.add(Food("Chiken Katsu", "Rp 13.000", R.drawable.katsu))
        foodList.add(Food("Jus Mangga", "Rp 5.000", R.drawable.jusmangga))


        // 3.2 Inisialisasi FoodAdapter (HARUS ADA DI SINI)
        foodAdapter = FoodAdapter(foodList) { clickedFood ->
            Toast.makeText(this, "${clickedFood.name} ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show()
        }

        // 3.3 Set adapter ke RecyclerView
        rvMostPopular.adapter = foodAdapter

        // --- 4. SETUP BOTTOM NAVIGATION (Logika Navigasi) ---
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> { true }
                R.id.nav_menu -> { Toast.makeText(this, "Pindah ke Halaman Menu", Toast.LENGTH_SHORT).show(); true }
                R.id.nav_order -> { Toast.makeText(this, "Pindah ke Halaman Order", Toast.LENGTH_SHORT).show(); true }
                R.id.nav_history -> { Toast.makeText(this, "Pindah ke Halaman Riwayat", Toast.LENGTH_SHORT).show(); true }
                else -> false
            }
        }
        bottomNavigationView.menu.findItem(R.id.nav_home).isChecked = true
    }

    private fun loadUserName() {
        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val username = sharedPref.getString(USERNAME_KEY, "Hyunsuk")

        if (::tvGreeting.isInitialized) {
            tvGreeting.text = "Hi, $username!"
        }
    }
}