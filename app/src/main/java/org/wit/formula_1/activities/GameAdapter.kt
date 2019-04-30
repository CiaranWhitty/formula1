package org.wit.formula_1.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_game.view.*
import org.wit.formula_1.R
import org.wit.formula_1.models.GameModel


interface GameListener {
    fun onGameClick(game: GameModel)
}

class GameAdapter constructor(private var games: List<GameModel>,
                                   private val listener: GameListener) : RecyclerView.Adapter<GameAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_game, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val game = games[holder.adapterPosition]
        holder.bind(game, listener)
    }

    override fun getItemCount(): Int = games.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(game: GameModel, listener: GameListener) {
            itemView.gameTitle.text = game.title
            itemView.gameDescription.text = game.description
            itemView.setOnClickListener { listener.onGameClick(game) }
        }
    }
}