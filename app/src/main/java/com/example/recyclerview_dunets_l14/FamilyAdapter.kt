package com.example.recyclerview_dunets_l14

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_dunets_l14.family_tree.Person

class FamilyAdapter(private val items: List<Pair<Person, Int>>, private val density: Float) :
    RecyclerView.Adapter<FamilyAdapter.PersonViewHolder>() {

    val dfltPd = (8 * density).toInt()

    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(name: String, age: Int, gap: Int) {
            val newPadding: Int = (16 * (gap + 1) * density).toInt()

            itemView.run {
                findViewById<LinearLayout>(R.id.personItemContainer)
                    .setPadding(newPadding, dfltPd, dfltPd, dfltPd)

                findViewById<TextView>(R.id.tvName).text = name

                findViewById<TextView>(R.id.tvAge).text =
                    itemView.context.getString(R.string.age_text, age)

                findViewById<TextView>(R.id.tvGap).text =
                    itemView.context.getString(R.string.gen_gap_text, gap)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_recycler_item, parent, false)

        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val pair = items[position]
        holder.bind(pair.first.name, pair.first.age, pair.second)
    }

    override fun getItemCount(): Int = items.size

}
