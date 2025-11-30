package com.candra.telkafers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity // Untuk AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager // Untuk GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager // Untuk LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView // Untuk RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView // Untuk BottomNavigationView


class HomeActivity : AppCompatActivity() {

    // Kunci yang digunakan untuk menyimpan dan mengambil nama pengguna
    private val USERNAME_KEY = "USERNAME_KEY"
    private val PREFS_NAME = "UserPref"

    // Deklarasi RecyclerView dan Adapter
    private lateinit var rvCategories: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryList: ArrayList<Category>

    private lateinit var rvMostPopular: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var foodList: ArrayList<Food>

    // Asumsi: TextView untuk sapaan memiliki ID tvGreeting di activity_home.xml
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

        // LOGIKA PINDAH KE KERANJANG (CartActivity)
        imgCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // LOGIKA Banner Order Button
        btnBannerOrder.setOnClickListener {
            Toast.makeText(this, "Ayam Geprek ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show()
        }

        // --- 2. SETUP KATEGORI (HORIZONTAL LIST) ---
        rvCategories = findViewById(R.id.rvCategories)
        rvCategories.setHasFixedSize(true)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        categoryList = ArrayList()

        // Data Kategori Sesuai Permintaan Anda: 4 item dengan urutan dan ikon yang spesifik
        categoryList.add(Category("Drinks", false, R.drawable.cafe))          // Urutan 1
        categoryList.add(Category("Main Course", false, R.drawable.ricebowl)) // Urutan 2
        categoryList.add(Category("Snacks", false, R.drawable.fries))        // Urutan 3
        categoryList.add(Category("Desserts", true, R.drawable.doughnut))    // Urutan 4 (Set as Default Selected)

        categoryAdapter = CategoryAdapter(categoryList)
        rvCategories.adapter = categoryAdapter

        // --- 3. SETUP MENU MAKANAN POPULER (GRID 2 KOLOM) ---
        rvMostPopular = findViewById(R.id.rvMostPopular)
        rvMostPopular.setHasFixedSize(true)
        rvMostPopular.layoutManager = GridLayoutManager(this, 2)

        foodList = ArrayList()
        // Menggunakan aset foto1, foto2, foto3 dari drawable
        foodList.add(Food("Ayam Geprek", "Rp 10.000", R.drawable.ayamgeprek))
        foodList.add(Food("Es Teh", "Rp 3.000", R.drawable.esteh))
        foodList.add(Food("Nescafe", "Rp 8.000", R.drawable.nescafe))
        foodList.add(Food("Pop Mie", "Rp 5.000", R.drawable.foto1))
        foodList.add(Food("Chiken Katsu", "Rp 10.000", R.drawable.foto2))
        foodList.add(Food("Mie Ayam", "Rp 12.000", R.drawable.foto3))

        foodAdapter = FoodAdapter(foodList)
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

    /**
     * Fungsi untuk mengambil nama pengguna dari SharedPreferences dan menampilkannya.
     */
    private fun loadUserName() {
        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val username = sharedPref.getString(USERNAME_KEY, "Hyunsuk") // Default Hyunsuk jika tidak ditemukan

        // Memastikan TextView sudah diinisialisasi
        if (::tvGreeting.isInitialized) {
            tvGreeting.text = "Hi, $username!"
        }
    }
}