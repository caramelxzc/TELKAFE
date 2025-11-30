package com.candra.telkafers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout // Untuk ConstraintLayout
import androidx.core.content.ContextCompat // Untuk ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val categoryList: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    // Definisikan ID warna sesuai colors.xml
    private val COLOR_RED_PRIMARY = R.color.red_primary
    private val COLOR_TEXT_DARK = R.color.text_dark

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryContainer: ConstraintLayout = itemView.findViewById(R.id.categoryContainer)
        val iconBackground: ConstraintLayout = itemView.findViewById(R.id.iconBackground)
        val imgCategoryIcon: ImageView = itemView.findViewById(R.id.imgCategoryIcon)
        val tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categoryList[position]
        val context = holder.itemView.context

        holder.tvCategoryName.text = item.name
        holder.imgCategoryIcon.setImageResource(item.iconResId)

        if (item.isSelected) {
            holder.iconBackground.setBackgroundResource(R.drawable.bg_category_selected)
            holder.tvCategoryName.setTextColor(ContextCompat.getColor(context, COLOR_RED_PRIMARY))
        } else {
            holder.iconBackground.setBackgroundResource(R.drawable.bg_category_unselected)
            holder.tvCategoryName.setTextColor(ContextCompat.getColor(context, COLOR_TEXT_DARK))
        }

        holder.categoryContainer.setOnClickListener {
            for (i in categoryList.indices) {
                categoryList[i].isSelected = (i == position)
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = categoryList.size
}