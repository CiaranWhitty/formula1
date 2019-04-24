package org.wit.formula_1.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.formula_1.models.GameMemStore
import org.wit.formula_1.models.PlayerMemStore

class MainApp : Application(), AnkoLogger {

    //val games = ArrayList<GameModel>()
    val games = GameMemStore()

    //val players = ArrayList<PlayerModel>()
    val players = PlayerMemStore()

    override fun onCreate() {
        super.onCreate()


        info("" + "Game started")
    }
}