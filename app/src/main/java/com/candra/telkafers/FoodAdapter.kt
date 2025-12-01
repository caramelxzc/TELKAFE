package com.candra.telkafers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// PERBAIKAN: Tambahkan parameter kedua (listener) ke konstruktor
class FoodAdapter(
    private val foodList: List<Food>,
    private val onItemClicked: (Food) -> Unit // <--- PASTI BARIS INI ADA
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFood: ImageView = itemView.findViewById(R.id.imgFood)
        val tvName: TextView = itemView.findViewById(R.id.tvFoodName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvFoodPrice)
        // ASUMSI: Tombol keranjang Anda memiliki ID btnAddCart (cek item_food.xml Anda)
        val btnAddCart: ImageView = itemView.findViewById(R.id.btnAddCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]

        holder.imgFood.setImageResource(food.imageResId)
        holder.tvName.text = food.name
        holder.tvPrice.text = food.price

        // Tambahkan Listener ke tombol keranjang (atau seluruh item)
        // Ini yang memanggil lambda 'onItemClicked'
        holder.btnAddCart.setOnClickListener {
            onItemClicked(food)
        }

        // Opsi lain: Jika klik di seluruh area item:
        /*
        holder.itemView.setOnClickListener {
             onItemClicked(food)
        }
        */
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}