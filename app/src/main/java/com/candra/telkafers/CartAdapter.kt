package com.candra.telkafers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val cartList: ArrayList<CartItem>,
    private val onQuantityChanged: () -> Unit // Callback untuk update total
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItem: ImageView = itemView.findViewById(R.id.imgCartItem)
        val tvName: TextView = itemView.findViewById(R.id.tvCartName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvCartPrice)
        val tvQty: TextView = itemView.findViewById(R.id.tvQuantity)
        val btnMinus: ImageView = itemView.findViewById(R.id.btnMinus)
        val btnPlus: ImageView = itemView.findViewById(R.id.btnPlus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartList[position]

        // Set Data
        holder.tvName.text = item.name
        holder.tvPrice.text = "Rp ${item.price.toInt()}"
        holder.tvQty.text = item.quantity.toString()
        holder.imgItem.setImageResource(item.imageResId) // Foto dari data

        // Logic Tombol Minus
        holder.btnMinus.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity--
                holder.tvQty.text = item.quantity.toString()
                onQuantityChanged() // Hitung ulang total
            }
        }

        // Logic Tombol Plus
        holder.btnPlus.setOnClickListener {
            item.quantity++
            holder.tvQty.text = item.quantity.toString()
            onQuantityChanged() // Hitung ulang total
        }
    }

    override fun getItemCount(): Int = cartList.size
}