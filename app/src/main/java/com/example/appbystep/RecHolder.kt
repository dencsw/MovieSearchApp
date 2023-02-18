package com.example.appbystep

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bind(item:FilmModel) {
        val vTitle = itemView.findViewById<TextView>(R.id.item_title)
        vTitle.text = item.nameRu
        val vGenre =itemView.findViewById<TextView>(R.id.item_genre)
        vGenre.text = "${item.genres[0].values}".drop(1).dropLast(1)
        val vYear = itemView.findViewById<TextView>(R.id.film_year)
        vYear.text = item.year
        val pic = itemView.findViewById<ImageView>(R.id.item_poster)
        Picasso.get()
            .load(item.posterUrlPreview)
            .into(pic)
        itemView.setOnLongClickListener(View.OnLongClickListener {
            val favstar = itemView.findViewById<ImageView>(R.id.favourite_star)
            favstar.visibility =  View.VISIBLE
            return@OnLongClickListener true
        })
    }
}
