package org.wit.formula_1.models

interface GameStore {
    fun findAll(): List<GameModel>
    fun create(game: GameModel)
    fun update(game: GameModel)
}