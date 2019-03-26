package org.wit.formula_1.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.formula_1.models.GameModel
import org.wit.formula_1.models.PlayerModel

class MainApp : Application(), AnkoLogger {

    val games = ArrayList<GameModel>()
    val players = ArrayList<PlayerModel>()

    override fun onCreate() {
        super.onCreate()


        info("" + "Game started")
    }
}