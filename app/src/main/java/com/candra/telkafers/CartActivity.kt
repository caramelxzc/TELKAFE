// CartActivity.kt
package com.candra.telkafers

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class CartActivity : AppCompatActivity() {

    private lateinit var rvCartItems: RecyclerView
    private lateinit var tvSubtotal: TextView
    private lateinit var btnCheckout: Button
    private lateinit var imgBack: ImageView
    private lateinit var summaryContainer: View
    private lateinit var tvEmptyCart: TextView

    // Ambil referensi langsung dari CartManager
    private val cartList = CartManager.cartItems
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Inisialisasi Views
        rvCartItems = findViewById(R.id.rvCartItems)
        tvSubtotal = findViewById(R.id.tvSubtotal)
        btnCheckout = findViewById(R.id.btnCheckout)
        imgBack = findViewById(R.id.imgBack)
        summaryContainer = findViewById(R.id.summaryContainer)
        tvEmptyCart = findViewById(R.id.tvEmptyCart)

        // 1. Setup RecyclerView
        rvCartItems.layoutManager = LinearLayoutManager(this)

        // 2. Inisialisasi Adapter DENGAN LISTENER onQuantityChanged
        cartAdapter = CartAdapter(cartList) { item, newQuantity ->
            handleQuantityChange(item, newQuantity)
        }
        rvCartItems.adapter = cartAdapter

        // 3. Tampilkan UI yang benar (Kosong atau Penuh)
        checkCartState()

        // 4. Setup Listener Aksi
        btnCheckout.setOnClickListener {
            if (cartList.isNotEmpty()) {
                Toast.makeText(this, "Pesanan Berhasil dibuat! Total: ${tvSubtotal.text}", Toast.LENGTH_LONG).show()
                CartManager.clearCart() // Kosongkan keranjang setelah checkout
                checkCartState() // Perbarui UI menjadi kosong
            }
        }

        imgBack.setOnClickListener { finish() }
    }

    /**
     * Menangani perubahan kuantitas item (tambah/kurang/hapus)
     */
    private fun handleQuantityChange(item: CartItem, newQuantity: Int) {
        val index = cartList.indexOfFirst { it.id == item.id }

        if (index != -1) {
            if (newQuantity > 0) {
                // Update kuantitas
                val updatedItem = cartList[index].copy(quantity = newQuantity)
                cartList[index] = updatedItem
                cartAdapter.notifyItemChanged(index)
            } else {
                // Hapus item jika kuantitas <= 0
                val removedName = cartList[index].foodName
                cartList.removeAt(index)
                cartAdapter.notifyItemRemoved(index)
                Toast.makeText(this, "$removedName dihapus dari keranjang.", Toast.LENGTH_SHORT).show()
            }
            checkCartState() // Periksa kembali state keranjang
        }
    }

    /**
     * Mengelola tampilan UI (penuh atau kosong).
     */
    private fun checkCartState() {
        if (cartList.isEmpty()) {
            tvEmptyCart.visibility = View.VISIBLE
            rvCartItems.visibility = View.GONE
            summaryContainer.visibility = View.GONE
        } else {
            tvEmptyCart.visibility = View.GONE
            rvCartItems.visibility = View.VISIBLE
            summaryContainer.visibility = View.VISIBLE
            updateTotal()
        }
    }

    private fun calculateTotal(): Int {
        var total = 0
        for (item in cartList) {
            total += item.basePrice * item.quantity
        }
        return total
    }

    private fun updateTotal() {
        val totalAmount = calculateTotal()

        // Format angka ke format mata uang Rupiah
        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        formatter.maximumFractionDigits = 0

        tvSubtotal.text = formatter.format(totalAmount)
    }
}