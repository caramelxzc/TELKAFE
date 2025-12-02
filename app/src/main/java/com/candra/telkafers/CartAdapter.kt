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

class CartAdapter(
    private val cartList: List<CartItem>,
    private val onQuantityChanged: (CartItem, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 🟢 PERBAIKAN: Memastikan semua TextView dan ImageView diinisialisasi dengan ID yang benar
        val imgCartItem: ImageView = itemView.findViewById(R.id.imgCartItem)
        val tvCartItemName: TextView = itemView.findViewById(R.id.tvCartItemName)
        val tvCartItemTotal: TextView = itemView.findViewById(R.id.tvCartItemTotal)

        val btnQtyMinus: TextView = itemView.findViewById(R.id.btnQtyMinus)
        val tvQtyValue: TextView = itemView.findViewById(R.id.tvQtyValue)
        val btnQtyPlus: TextView = itemView.findViewById(R.id.btnQtyPlus)

        fun bind(item: CartItem, onQuantityChanged: (CartItem, Int) -> Unit) {
            val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
            formatter.maximumFractionDigits = 0

            // 🟢 PERBAIKAN: Muat gambar dari Resource ID yang tersimpan di CartItem
            // Ini akan memuat gambar ayamgeprek.png, esteh.png, dll.
            imgCartItem.setImageResource(item.imageResourceId)

            tvCartItemName.text = item.foodName
            tvCartItemTotal.text = formatter.format(item.basePrice)
            tvQtyValue.text = item.quantity.toString()

            // --- Logika Tombol Kuantitas ---

            btnQtyPlus.setOnClickListener {
                onQuantityChanged(item, item.quantity + 1)
            }

            btnQtyMinus.setOnClickListener {
                if (item.quantity > 1) {
                    onQuantityChanged(item, item.quantity - 1)
                } else {
                    // Logic untuk menghapus item jika kuantitas mencapai 0
                    onQuantityChanged(item, 0)
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