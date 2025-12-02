// CanteenAdapter.kt
package com.candra.telkafers

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CanteenAdapter(
    private val canteenList: List<Canteen>,
    private val clickListener: (Canteen) -> Unit // Untuk menangani klik pada item
) : RecyclerView.Adapter<CanteenAdapter.CanteenViewHolder>() {

    inner class CanteenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 🚨 PERBAIKAN: Memastikan ID yang dicari ada di item_canteen.xml (seperti yang Anda definisikan)
        val imgCanteen: ImageView = itemView.findViewById(R.id.imgCanteen)
        val tvCanteenName: TextView = itemView.findViewById(R.id.tvCanteenName)       // 🟢 Ditemukan di XML (Perbaikan 1)
        val tvCanteenDescription: TextView = itemView.findViewById(R.id.tvCanteenDescription) // 🟢 Ditemukan di XML (Perbaikan 2)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)                 // 🟢 Ditemukan di XML (Perbaikan 3)

        fun bind(canteen: Canteen, clickListener: (Canteen) -> Unit) {
            imgCanteen.setImageResource(canteen.imageUrl)
            tvCanteenName.text = canteen.name
            tvCanteenDescription.text = canteen.description

            // Logika Status Buka/Tutup
            if (canteen.isOpen) {
                tvStatus.text = "OPEN"
                tvStatus.setTextColor(Color.parseColor("#00AA00")) // Hijau
            } else {
                tvStatus.text = "CLOSED"
                tvStatus.setTextColor(Color.parseColor("#FF0000")) // Merah
            }

            itemView.setOnClickListener {
                clickListener(canteen)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CanteenViewHolder {
        // ASUMSI: Layout file yang digunakan adalah R.layout.item_canteen
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_canteen, parent, false)
        return CanteenViewHolder(view)
    }

    override fun onBindViewHolder(holder: CanteenViewHolder, position: Int) {
        holder.bind(canteenList[position], clickListener)
    }

    override fun getItemCount(): Int = canteenList.size
}