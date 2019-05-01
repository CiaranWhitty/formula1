package org.wit.formula_1.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.formula_1.helpers.*
import java.util.*

val JSON_FILE = "games.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<GameModel>>() {}.type

fun generateRandomIdG(): Long {
    return Random().nextLong()
}

class GameJSONStore : GameStore, AnkoLogger {

    val context: Context
    var games = mutableListOf<GameModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<GameModel> {
        return games
    }

    override fun create(game: GameModel) {
        game.gid = generateRandomIdG()
        games.add(game)
        serialize()
    }


    override fun update(game: GameModel) {
        val gamesList = findAll() as ArrayList<GameModel>
        var foundGame: GameModel? = gamesList.find { p -> p.gid == game.gid }
        if (foundGame != null) {
            foundGame.title = game.title
            foundGame.description = game.description
        }
        serialize()
    }


    override fun delete(game: GameModel) {
        games.remove(game)
        serialize()
    }


    private fun serialize() {
        val jsonString = gsonBuilder.toJson(games, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        games = Gson().fromJson(jsonString, listType)
    }
}