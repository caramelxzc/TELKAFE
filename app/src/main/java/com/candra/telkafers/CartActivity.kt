package com.candra.telkafers

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var rvCartItems: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartList: ArrayList<CartItem>
    private lateinit var tvTotalPrice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // 1. Inisialisasi View
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnCheckout = findViewById<Button>(R.id.btnCheckout)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        rvCartItems = findViewById(R.id.rvCartItems)

        // 2. Navigasi Back
        btnBack.setOnClickListener { finish() }

        // 3. Setup RecyclerView
        rvCartItems.layoutManager = LinearLayoutManager(this)
        rvCartItems.setHasFixedSize(true)

        // 4. Isi Data Dummy (Menggunakan foto1, foto2, foto3)
        cartList = ArrayList()
        cartList.add(CartItem("Ayam Geprek", 15000.0, R.drawable.foto1, 1))
        cartList.add(CartItem("Nasi Goreng", 12000.0, R.drawable.foto2, 2))
        cartList.add(CartItem("Mie Goreng", 10000.0, R.drawable.foto3, 1))

        // 5. Pasang Adapter & Callback Hitung Total
        cartAdapter = CartAdapter(cartList) {
            calculateTotal() // Dipanggil saat jumlah berubah
        }
        rvCartItems.adapter = cartAdapter

        // 6. Hitung total awal
        calculateTotal()

        // 7. Tombol Checkout
        btnCheckout.setOnClickListener {
            Toast.makeText(this, "Checkout Berhasil! Total: ${tvTotalPrice.text}", Toast.LENGTH_SHORT).show()
        }
    }

    // Fungsi untuk menghitung total harga
    private fun calculateTotal() {
        var total = 0.0
        for (item in cartList) {
            total += (item.price * item.quantity)
        }
        tvTotalPrice.text = "Rp ${total.toInt()}"
    }
}