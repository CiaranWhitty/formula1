package org.wit.formula_1.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_player.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.formula_1.R
import org.wit.formula_1.main.MainApp
import org.wit.formula_1.models.PlayerModel

class PlayerActivity : AppCompatActivity(), AnkoLogger {

    var player = PlayerModel()
    lateinit var app: MainApp

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_player)
    app = application as MainApp

    toolbarAddPlayer.title = title
    setSupportActionBar(toolbarAddPlayer)

    btnAddPlayer.requestFocus()

    btnAddPlayer.setOnClickListener {

        player.name = playerName.text.toString()
        player.points = playerPoints.text.toString()

        if (player.name.isNotEmpty()) {
            app.players.add(player.copy())
            info("add Button Pressed: $player")
            app.players.forEach { info("add Button Pressed: ${it}") }
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        } else {
            toast("Please Enter a title")
        }

    }


}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_player, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override  fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel_player -> {
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

}
