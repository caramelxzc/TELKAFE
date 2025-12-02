package com.candra.telkafers

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.candra.telkafers.R

// Catatan: Pastikan Canteen (Data Model) dan CanteenAdapter (Kelas Adapter)
// sudah didefinisikan dalam file Kotlin terpisah di package yang sama (com.candra.telkafers).

class MenuActivity : AppCompatActivity() {

    private lateinit var rvCanteens: RecyclerView
    private lateinit var canteenAdapter: CanteenAdapter
    private lateinit var canteenList: ArrayList<Canteen>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        rvCanteens = findViewById(R.id.rvCanteens)

        // 1. Persiapan Data Kantin Mockup
        canteenList = generateMockCanteenData()

        // 2. Setup RecyclerView
        rvCanteens.setHasFixedSize(true)
        rvCanteens.layoutManager = LinearLayoutManager(this)

        // 3. Inisialisasi dan Set Adapter
        canteenAdapter = CanteenAdapter(canteenList) { clickedCanteen ->
            // Menangani klik item kantin. Menggunakan this@MenuActivity untuk konteks yang benar
            Toast.makeText(this@MenuActivity, "Anda memilih ${clickedCanteen.name}. Pindah ke Detail Menu...", Toast.LENGTH_SHORT).show()

            // TODO: IMPLEMENTASI PINDAH KE DETAIL MENU DI SINI
            // val intent = Intent(this@MenuActivity, DetailMenuActivity::class.java)
            // intent.putExtra("CANTEEN_NAME", clickedCanteen.name)
            // startActivity(intent)
        }
        rvCanteens.adapter = canteenAdapter

        // 4. Setup Bottom Navigation View
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // SINTAKS INTENT DIPERBAIKI: Menggunakan this@MenuActivity
                    startActivity(Intent(this@MenuActivity, HomeActivity::class.java))
                    finish() // Tutup Menu Activity agar tidak bertumpuk
                    true
                }
                R.id.nav_menu -> {
                    true // Sudah berada di Menu Activity
                }
                R.id.nav_order -> {
                    Toast.makeText(this@MenuActivity, "Pindah ke Halaman Order", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_history -> {
                    Toast.makeText(this@MenuActivity, "Pindah ke Halaman Riwayat", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // Tandai ikon Menu (Garpu & Sendok) terpilih
        bottomNavigationView.menu.findItem(R.id.nav_menu).isChecked = true
    }

    /**
     * Fungsi Mock Data Kantin (Hanya menggunakan ayamgeprek dan ricebowl)
     */
    private fun generateMockCanteenData(): ArrayList<Canteen> {
        val list = ArrayList<Canteen>()

        // MENGGUNAKAN HANYA AYAMGEPREK DAN RICEBOWL
        list.add(Canteen("Kantin 1 - QTA", "Ayam Fillet, Nasi Goreng", R.drawable.katsu, true))
        list.add(Canteen("Kantin 2 - Saba", "Ayam Geprek, Aneka Minuman", R.drawable.ayamgeprek, true))
        list.add(Canteen("Kantin 3", "Mie Rebus, Nasi Kuning", R.drawable.ayamgeprek, false))
        list.add(Canteen("Kantin 4", "Roti Bakar, Snack Ringan", R.drawable.katsu, true))
        list.add(Canteen("Kantin 5", "Minuman Dingin, Kopi", R.drawable.ayamgeprek, false))
        list.add(Canteen("Kantin 6", "Nasi Uduk, Soto Ayam", R.drawable.katsu, true))

        return list
    }
}