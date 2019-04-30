package org.wit.formula_1.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.formula_1.models.*

class MainApp : Application(), AnkoLogger {

    lateinit var games: GameStore
    lateinit var players: PlayerStore

    override fun onCreate() {
        super.onCreate()

        games = GameJSONStore(applicationContext)

        players = PlayerMemStore()

        info("" + "Game started")
    }
}