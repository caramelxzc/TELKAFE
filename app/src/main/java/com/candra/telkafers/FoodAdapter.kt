// FoodAdapter.kt
package com.candra.telkafers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class FoodAdapter(
    private val foodList: List<Food>,
    private val onItemClicked: (Food) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFood: ImageView = itemView.findViewById(R.id.imgFood)
        val tvName: TextView = itemView.findViewById(R.id.tvFoodName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvFoodPrice)

        // 🟢 PERBAIKAN KRITIS: ID diubah dari R.id.imgAddToCart menjadi R.id.btnAddCart
        // Agar sesuai dengan android:id="@+id/btnAddCart" di item_food.xml Anda.
        val btnAddCart: ImageView = itemView.findViewById(R.id.btnAddCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]

        // Format harga ke Rupiah
        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        formatter.maximumFractionDigits = 0

        // Asumsi food.imageUrl dan food.price: Int sudah diperbaiki di Food.kt
        holder.imgFood.setImageResource(food.imageUrl)
        holder.tvName.text = food.name
        holder.tvPrice.text = formatter.format(food.price)

        // Listener memanggil CartManager.addItem() melalui HomeActivity
        holder.btnAddCart.setOnClickListener {
            onItemClicked(food)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}