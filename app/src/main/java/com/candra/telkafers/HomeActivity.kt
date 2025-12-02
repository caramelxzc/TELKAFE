// HomeActivity.kt

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

class HomeActivity : AppCompatActivity() {

    private val USERNAME_KEY = "USERNAME_KEY"
    private val PREFS_NAME = "UserPref"

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var rvMostPopular: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var foodList: ArrayList<Food>

    private lateinit var tvGreeting: TextView
    private lateinit var imgBannerFood: ImageView

    // Data item Ayam Geprek untuk Banner
    private val favoriteBannerFood = Food(
        id = 100, // ID unik untuk item banner
        name = "Ayam Geprek",
        price = 10000,
        imageUrl = R.drawable.ayamgeprek // ID Resource gambar
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // --- 0. AMBIL NAMA PENGGUNA DARI LOKAL (SharedPreferences) ---
        tvGreeting = findViewById(R.id.tvGreeting)
        loadUserName()

        // --- 1. SETUP HEADER & ACTION ---
        val imgCart = findViewById<ImageView>(R.id.imgCart)
        val btnBannerOrder = findViewById<Button>(R.id.btnBannerOrder)

        imgBannerFood = findViewById(R.id.imgBannerFood)
        imgBannerFood.setImageResource(favoriteBannerFood.imageUrl)

        // Aksi Ikon Keranjang (My Basket)
        imgCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Aksi Tombol Order Now di Banner
        btnBannerOrder.setOnClickListener {
            CartManager.addItem(favoriteBannerFood)
            Toast.makeText(this, "${favoriteBannerFood.name} ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show()
        }

        // --- 2. SETUP KATEGORI (HORIZONTAL LIST) ---
        rvCategories = findViewById(R.id.rvCategories)
        rvCategories.setHasFixedSize(true)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // 🟢 PERBAIKAN: INISIALISASI DATA KATEGORI
        val categoryList = ArrayList<Category>()

        // Catatan: Ganti R.drawable.ic_drink, dsb., dengan NAMA RESOURCE IKON ANDA yang sebenarnya.
        categoryList.add(Category(name = "Drinks", iconResId = R.drawable.cafe, isSelected = true))
        categoryList.add(Category(name = "Main Course", iconResId = R.drawable.ricebowl))
        categoryList.add(Category(name = "Snacks", iconResId = R.drawable.fries))
        categoryList.add(Category(name = "Desserts", iconResId = R.drawable.doughnut))

        categoryAdapter = CategoryAdapter(categoryList)
        rvCategories.adapter = categoryAdapter

        // --- 3. SETUP MENU MAKANAN POPULER (GRID 2 KOLOM) ---
        rvMostPopular = findViewById(R.id.rvMostPopular)
        rvMostPopular.setHasFixedSize(false)

        val spacingInDp = 16
        val spacingInPixels = (spacingInDp * resources.displayMetrics.density).toInt()

        val gridLayoutManager = GridLayoutManager(this, 2)
        rvMostPopular.layoutManager = gridLayoutManager

        // Asumsi GridSpacingItemDecoration.kt tersedia
        rvMostPopular.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount = 2,
                spacing = spacingInPixels,
                includeEdge = false
            )
        )

        // INISIALISASI foodList
        foodList = ArrayList()
        foodList.add(Food(1, "Ayam Geprek", 10000, R.drawable.ayamgeprek))
        foodList.add(Food(2, "Es Teh", 3000, R.drawable.esteh))
        foodList.add(Food(3, "Nescafe", 8000, R.drawable.nescafe))
        foodList.add(Food(4, "Pop Mie", 5000, R.drawable.popmie))
        foodList.add(Food(5, "Chiken Katsu", 13000, R.drawable.katsu))
        foodList.add(Food(6, "Jus Mangga", 5000, R.drawable.jusmangga))

        // Inisialisasi FoodAdapter dengan listener CartManager
        foodAdapter = FoodAdapter(foodList) { clickedFood ->
            CartManager.addItem(clickedFood)
            Toast.makeText(this, "${clickedFood.name} ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, CartActivity::class.java))
        }

        // Set adapter ke RecyclerView
        rvMostPopular.adapter = foodAdapter

        // --- 4. SETUP BOTTOM NAVIGATION (Logika Navigasi) ---
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> { true }
                R.id.nav_menu -> {
                    startActivity(Intent(this, MenuActivity::class.java))
                    true
                }
                R.id.nav_order -> { Toast.makeText(this, "Pindah ke Halaman Order", Toast.LENGTH_SHORT).show(); true }
                R.id.nav_history -> { Toast.makeText(this, "Pindah ke Halaman Riwayat", Toast.LENGTH_SHORT).show(); true }
                else -> false
            }
        }
        bottomNavigationView.menu.findItem(R.id.nav_home).isChecked = true
    }

    private fun loadUserName() {
        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val username = sharedPref.getString(USERNAME_KEY, "User")

        if (::tvGreeting.isInitialized) {
            tvGreeting.text = "Hi, $username!"
        }
    }
}