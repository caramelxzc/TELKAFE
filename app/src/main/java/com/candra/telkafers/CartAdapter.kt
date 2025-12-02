// CartAdapter.kt

package com.candra.telkafers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

// Definisikan Click Listener untuk mengupdate kuantitas di CartActivity
class CartAdapter(
    private val cartList: List<CartItem>,
    // Listener ini akan dipanggil saat tombol + atau - ditekan
    private val onQuantityChanged: (CartItem, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCartItem: ImageView = itemView.findViewById(R.id.imgCartItem)
        val tvCartItemName: TextView = itemView.findViewById(R.id.tvCartItemName)
        val tvCartItemTotal: TextView = itemView.findViewById(R.id.tvCartItemTotal)

        // Views baru untuk kontrol kuantitas
        val btnQtyMinus: TextView = itemView.findViewById(R.id.btnQtyMinus)
        val tvQtyValue: TextView = itemView.findViewById(R.id.tvQtyValue)
        val btnQtyPlus: TextView = itemView.findViewById(R.id.btnQtyPlus)

        fun bind(item: CartItem, onQuantityChanged: (CartItem, Int) -> Unit) {
            val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
            formatter.maximumFractionDigits = 0

            // Tampilkan data item
            // HANYA MENGGUNAKAN DUA GAMBAR MOCK
            val displayImage = if (item.id % 2 == 0) R.drawable.ricebowl else R.drawable.ayamgeprek
            imgCartItem.setImageResource(displayImage)

            tvCartItemName.text = item.foodName
            tvCartItemTotal.text = formatter.format(item.basePrice) // Menampilkan Harga Satuan
            tvQtyValue.text = item.quantity.toString()

            // --- Logika Tombol Kuantitas ---

            // Tombol Tambah
            btnQtyPlus.setOnClickListener {
                // Panggil listener dengan kuantitas baru (kuantitas + 1)
                onQuantityChanged(item, item.quantity + 1)
            }

            // Tombol Kurangi
            btnQtyMinus.setOnClickListener {
                if (item.quantity > 1) {
                    // Panggil listener dengan kuantitas baru (kuantitas - 1)
                    onQuantityChanged(item, item.quantity - 1)
                } else {
                    // TODO: Peringatan atau logika penghapusan item jika kuantitas menjadi 0
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartList[position], onQuantityChanged)
    }

    override fun getItemCount(): Int = cartList.size
}