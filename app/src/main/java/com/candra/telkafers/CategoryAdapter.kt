package com.candra.telkafers

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val categoryList: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvCategoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.tvName.text = category.name

        // Ubah warna background jika dipilih
        if (category.isSelected) {
            holder.tvName.setBackgroundResource(R.drawable.bg_category_selected)
            holder.tvName.setTextColor(Color.WHITE)
        } else {
            holder.tvName.setBackgroundResource(R.drawable.bg_category_unselected)
            holder.tvName.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int = categoryList.size
}