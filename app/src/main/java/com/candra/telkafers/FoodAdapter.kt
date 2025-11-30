package com.candra.telkafers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton // Jika ada tombol cart
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val foodList: List<Food>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // --- TAMBAHKAN INI ---
        val imgFood: ImageView = itemView.findViewById(R.id.imgFood) // ID ImageView di item_food.xml
        // ---------------------

        val tvName: TextView = itemView.findViewById(R.id.tvFoodName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvFoodPrice)
        // Tambahkan ImageButton untuk Cart jika ada di item_food.xml
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]

        // --- TAMBAHKAN INI ---
        holder.imgFood.setImageResource(food.imageResId) // Set gambar dari data Food
        // ---------------------

        holder.tvName.text = food.name
        holder.tvPrice.text = food.price

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Memilih: ${food.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}