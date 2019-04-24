package org.wit.formula_1.models

interface PlayerStore {
    fun findAll(): List<PlayerModel>
    fun create(player: PlayerModel)
    fun update(player: PlayerModel)


}