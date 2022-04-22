package com.vasquez.recyclerview_list.HeroeList


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vasquez.recyclerview_list.R
import com.vasquez.recyclerview_list.data.Heroe


class HeroesAdapter(private val onClick: (Heroe) -> Unit) :
    ListAdapter<Heroe, HeroesAdapter.HeroeViewHolder>(HeroeDiffCallback) {

    /* ViewHolder for Flower, takes in the inflated view and the onClick behavior. */
    class HeroeViewHolder(itemView: View, val onClick: (Heroe) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val heroeTextView: TextView = itemView.findViewById(R.id.heroe_text)
        private val heroeImageView: ImageView = itemView.findViewById(R.id.heroe_image)
        private var currentHeroe: Heroe? = null

        init {
            itemView.setOnClickListener {
                currentHeroe?.let {
                    onClick(it)
                }
            }
        }

        /* Bind flower name and image. */
        fun bind(heroe: Heroe) {
            currentHeroe = heroe

            heroeTextView.text = heroe.name
            if (heroe.image != null) {
                heroeImageView.setImageResource(heroe.image)
            } else {
                heroeImageView.setImageResource(R.drawable.ic_add_black_24dp)
            }
        }
    }

    /* Creates and inflates view and return HeroeViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.heroe_item, parent, false)
        return HeroeViewHolder(view, onClick)
    }

    /* Gets current flower and uses it to bind view. */
    override fun onBindViewHolder(holder: HeroeViewHolder, position: Int) {
        val heroe = getItem(position)
        holder.bind(heroe)

    }
}

object HeroeDiffCallback : DiffUtil.ItemCallback<Heroe>() {
    override fun areItemsTheSame(oldItem: Heroe, newItem: Heroe): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Heroe, newItem: Heroe): Boolean {
        return oldItem.id == newItem.id
    }
}