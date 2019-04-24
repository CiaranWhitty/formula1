package org.wit.formula_1.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastgId = 0L

internal fun getgId(): Long {
    return lastgId++
}

class GameMemStore : GameStore, AnkoLogger {

    val games = ArrayList<GameModel>()

    override fun findAll(): List<GameModel> {
        return games
    }

    override fun create(game: GameModel) {
        game.gid = getgId()
        games.add(game)
        logAll()
    }

    override fun update(game: GameModel) {
        var foundGame: GameModel? = games.find { p -> p.gid== game.gid }
        if (foundGame != null) {
            foundGame.title = game.title
            foundGame.description = game.description
            logAll()
        }
    }

    fun logAll() {
        games.forEach{ info("${it}") }
    }
}