package com.example.appfigurasaccion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appfigurasaccion.R
import com.example.appfigurasaccion.data.Figura


class FiguraAdapter(private val figuras: List<Figura>, private val onFiguraClick: (Figura) -> Unit) :
    RecyclerView.Adapter<FiguraAdapter.FiguraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiguraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_figura, parent, false)
        return FiguraViewHolder(view)
    }

    override fun onBindViewHolder(holder: FiguraViewHolder, position: Int) {
        val figura = figuras[position]
        holder.bind(figura)
    }

    override fun getItemCount(): Int {
        return figuras.size
    }

    inner class FiguraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreFigura)
        private val logoImageView: ImageView = itemView.findViewById(R.id.logoFigura)

        fun bind(figura: Figura) {
            nombreTextView.text = figura.nombre
            Glide.with(itemView.context).load(figura.logo).into(logoImageView)
            itemView.setOnClickListener {
                onFiguraClick(figura)
            }
        }
    }
}