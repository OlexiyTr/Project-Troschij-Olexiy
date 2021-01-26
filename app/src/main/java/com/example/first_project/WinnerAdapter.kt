package com.example.first_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.winner_member.view.*

class WinnerAdapter() : RecyclerView.Adapter<ExampleViewHolder>() {
    val winners: ArrayList<TeamScore> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.winner_member, parent, false)
        return ExampleViewHolder(itemView)
    }

    override fun getItemCount() = winners.size

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bind(winners[position])
    }

    fun addItemToList(item : TeamScore){
        winners.add(item)
        winners.sortByDescending { it.score }
        notifyItemInserted(winners.size - 1)
    }

}

class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(winner : TeamScore){
        val line = "${winner.name} ${winner.score}"
        itemView.winnerTeam.text = line
    }
}