package com.UGD.kurirku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.UGD.kurirku.entity.Pengiriman

class RVPengirimanAdapter(private val data: Array<Pengiriman>) : RecyclerView.Adapter<RVPengirimanAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_pengiriman, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder:viewHolder, position: Int){
        val currentItem = data[position]
        holder.tvNama.text = currentItem.kategori
        holder.tvDetails.text = "${currentItem.waktu}"
        holder.tvHarga.text =" ${currentItem.harga}"
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvNama : TextView = itemView.findViewById(R.id.chapter)
        val tvDetails : TextView = itemView.findViewById(R.id.itemDetail)
        val tvHarga : TextView = itemView.findViewById(R.id.itemTitle)

    }
}