package org.wit.formula_1.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.formula_1.models.GameModel

class MainApp : Application(), AnkoLogger {

    val games = ArrayList<GameModel>()

    override fun onCreate() {
        super.onCreate()

        games.add(GameModel("One", "About one..."))
        games.add(GameModel("Two", "About two..."))
        games.add(GameModel("Three", "About three..."))

        info("" + "Game started")
    }
}