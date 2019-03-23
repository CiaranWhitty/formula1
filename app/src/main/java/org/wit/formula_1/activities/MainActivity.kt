package org.wit.formula_1.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.wit.formula_1.R
import org.wit.formula_1.main.MainApp
import org.wit.formula_1.models.GameModel

class MainActivity : AppCompatActivity(), AnkoLogger {

    var game = GameModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        btnAdd.requestFocus();

        info("MainActivity has started")

        btnAdd.setOnClickListener() {

            game.title = gameTitle.text.toString()
            game.description = gameDescription.text.toString()

            if (game.title.isNotEmpty()) {
                app.games.add(game.copy())
                info("add Button Pressed: $game")
                app.games.forEach { info("add Button Pressed: ${it}") }
            } else {
                toast("Please Enter a title")
            }
        }



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override  fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
            R.id.item_add_player -> {
                startActivityForResult<PlayerActivity>(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }




}
