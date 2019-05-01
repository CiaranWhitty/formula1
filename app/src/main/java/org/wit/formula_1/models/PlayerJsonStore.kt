package org.wit.formula_1.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.formula_1.helpers.exists
import org.wit.formula_1.helpers.read
import org.wit.formula_1.helpers.write
import java.util.*

val JSON_FILE_P = "player.json"
val gsonBuilder_P = GsonBuilder().setPrettyPrinting().create()
val listType_P = object : TypeToken<ArrayList<PlayerModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PlayerJsonStore : PlayerStore, AnkoLogger {

    val context: Context
    var players = mutableListOf<PlayerModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE_P)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PlayerModel> {
        return players
    }

    override fun create(player: PlayerModel) {
        player.pid = generateRandomId()
        players.add(player)
        serialize()
    }


    override fun update(player: PlayerModel) {
        val playerList = findAll() as ArrayList<PlayerModel>
        var foundPlayer: PlayerModel? = playerList.find { p -> p.pid == player.pid }
        if (foundPlayer != null) {
            foundPlayer.name = player.name
            foundPlayer.points = player.points
        }
        serialize()
    }

    override fun delete(player: PlayerModel) {
        players.remove(player)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder_P.toJson(players, listType_P)
        write(context, JSON_FILE_P, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE_P)
        players = Gson().fromJson(jsonString, listType_P)
    }
}