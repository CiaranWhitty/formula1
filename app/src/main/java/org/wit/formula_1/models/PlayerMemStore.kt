package org.wit.formula_1.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastpId = 0L

internal fun getpId(): Long {
    return lastpId++
}

class PlayerMemStore : PlayerStore, AnkoLogger {

    val players = ArrayList<PlayerModel>()

    override fun findAll(): List<PlayerModel> {
        return players
    }

    /*override fun findPlayers(player: PlayerModel): List<PlayerModel> {

        return players
    }
*/
    override fun create(player: PlayerModel) {
        player.pid = getpId()
        players.add(player)
        logAll()
    }

    override fun update(player: PlayerModel) {
        var foundPlayer: PlayerModel? = players.find { p -> p.pid== player.pid }
        if (foundPlayer != null) {
            foundPlayer.name = player.name
            foundPlayer.points = player.points
            foundPlayer.image = player.image
            logAll()
        }
    }

    override fun delete(player: PlayerModel) {
        players.remove(player)
    }

    fun logAll() {
        players.forEach{ info("${it}") }
    }
}