package org.wit.formula_1.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.formula_1.R
import org.wit.formula_1.main.MainApp
import org.wit.formula_1.models.GameModel

class MainActivity : AppCompatActivity(), AnkoLogger {

    var game = GameModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp

        info("MainActivity has started")

        btnAdd.setOnClickListener() {

            game.title = gameTitle.text.toString()
            game.description = gameDescription.text.toString()

            if (game.title.isNotEmpty()) {
                app.games.add(game.copy())
                info("add Button Pressed: $game")
                app.games.forEach { info("add Button Pressed: ${it}")}
            }
            else {
                toast ("Please Enter a title")
            }
        }



    }
}
