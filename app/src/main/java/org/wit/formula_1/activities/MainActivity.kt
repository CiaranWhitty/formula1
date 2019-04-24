package org.wit.formula_1.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.activity_game_list.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_player.view.*
import org.jetbrains.anko.*
import org.wit.formula_1.R
import org.wit.formula_1.main.MainApp
import org.wit.formula_1.models.GameModel
import org.wit.formula_1.models.PlayerModel

class MainActivity : AppCompatActivity(), AnkoLogger, PlayerListener {

    var game = GameModel()
    lateinit var app: MainApp
    var editG = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        val layoutManager = LinearLayoutManager(this)
        recyclerViewPlayer.layoutManager = layoutManager
        recyclerViewPlayer.adapter = PlayerAdapter(app.players.findAll(),this)

        btnAdd.requestFocus()

        info("MainActivity has started")

        if (intent.hasExtra("game_edit")) {
            editG = true
            game = intent.extras.getParcelable<GameModel>("game_edit")
            gameTitle.setText(game.title)
            gameDescription.setText(game.description)
            btnAdd.setText(R.string.save_game)
        }


        btnAdd.setOnClickListener() {
            game.title = gameTitle.text.toString()
            game.description= gameDescription.text.toString()
            if (game.title.isEmpty()) {
                toast(R.string.enter_game_title)
            } else {
                if (editG) {
                    app.games.update(game.copy())
                } else {
                    app.games.create(game.copy())
                }
            }
            info("add Button Pressed: $gameTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

    }


    override fun onPlayerClick(player: PlayerModel) {
        startActivityForResult(intentFor<PlayerActivity>().putExtra("player_edit", player),0)
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

