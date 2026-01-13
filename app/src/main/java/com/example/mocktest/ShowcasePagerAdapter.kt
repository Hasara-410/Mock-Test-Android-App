package com.example.mocktest

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ShowcasePagerAdapter(
    private val items: List<Uri?>
) : RecyclerView.Adapter<ShowcasePagerAdapter.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_showcase_card, parent, false)
        return VH(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val uri = items[position]
        if (uri != null) holder.img.setImageURI(uri)
        else holder.img.setImageResource(android.R.drawable.ic_menu_gallery)
    }
}
