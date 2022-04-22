package com.vasquez.recyclerview_list.HeroeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vasquez.recyclerview_list.R

/* A list always displaying one element: the number of flowers. */

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var heroeCount: Int = 0

    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val heroeNumberTextView: TextView = itemView.findViewById(R.id.heroe_number_text)

        fun bind(heroeCount: Int) {
            heroeNumberTextView.text = heroeCount.toString()
        }
    }

    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)
        return HeaderViewHolder(view)
    }

    /* Binds number of flowers to the header. */
    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(heroeCount)
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return 1
    }

    /* Updates header to display number of flowers when a flower is added or subtracted. */
    fun updateHeroeCount(updatedHeroeCount: Int) {
        heroeCount = updatedHeroeCount
        notifyDataSetChanged()
    }


}