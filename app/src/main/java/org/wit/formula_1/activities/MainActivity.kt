package org.wit.formula_1.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.wit.formula_1.R
import org.wit.formula_1.main.MainApp
import org.wit.formula_1.models.GameModel
import org.wit.formula_1.models.PlayerModel


class MainActivity : AppCompatActivity(), AnkoLogger, PlayerListener {

    var game = GameModel()
    lateinit var app: MainApp
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarAdd.title = "Game-"
        setSupportActionBar(toolbarAdd)

        info("Main Activity started..")

        app = application as MainApp

        if (intent.hasExtra("game_edit")) {
            edit = true
            game = intent.extras.getParcelable<GameModel>("game_edit")
            gameTitle.setText(game.title)
            gameDescription.setText(game.description)
            btnAdd.setText(R.string.save_game)
        }

        btnAdd.setOnClickListener() {
            game.title = gameTitle.text.toString()
            game.description = gameDescription.text.toString()

            if (game.title.isEmpty()) {
                toast(R.string.enter_game_title)
            } else {
                if (edit) {
                    app.games.update(game.copy())
                } else {

                    app.games.create(game.copy())
                }
            }
            info("add Button Pressed: $gameTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }



        val layoutManager = LinearLayoutManager(this)
        recyclerViewPlayer.layoutManager = layoutManager
        recyclerViewPlayer.adapter = PlayerAdapter(app.players.findAll(), this)
        loadPlayers()




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.item_delete_game -> {
                app.games.delete(game)
                finish()
            }

            R.id.item_add_player -> startActivityForResult<PlayerActivity>(0)

            R.id.item_cancel -> {
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }





        private fun loadPlayers() {
            showPlayers( app.players.findAll())
        }

        fun showPlayers (players: List<PlayerModel>) {
            recyclerViewPlayer.adapter = PlayerAdapter(players, this)
            recyclerViewPlayer.adapter?.notifyDataSetChanged()
        }
        override fun onPlayerClick(player: PlayerModel) {
        startActivityForResult(intentFor<PlayerActivity>().putExtra("player_edit", player),0)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            loadPlayers()
            super.onActivityResult(requestCode, resultCode, data)
        }

}
