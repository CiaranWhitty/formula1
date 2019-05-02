package org.wit.formula_1.models

interface PlayerStore {
    fun findAll(): List<PlayerModel>
    fun create(player: PlayerModel)
    fun update(player: PlayerModel)
    fun delete(player: PlayerModel)

    //fun findPlayers(player: PlayerModel): List<PlayerModel>

}