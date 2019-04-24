package org.wit.formula_1.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_player.view.*
import org.wit.formula_1.R
import org.wit.formula_1.models.PlayerModel

class PlayerAdapter constructor(private var players: List<PlayerModel>,
                                private val listener: PlayerListener) : RecyclerView.Adapter<PlayerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_player, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val player = players[holder.adapterPosition]
        holder.bind(player, listener)
    }

    override fun getItemCount(): Int = players.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(player: PlayerModel, listener: PlayerListener) {
            itemView.playerName.text = player.name
            itemView.playerPoints.text = player.points
            itemView.setOnClickListener { listener.onPlayerClick(player) }

        }
    }
}

interface PlayerListener {
    fun onPlayerClick(player: PlayerModel)
}