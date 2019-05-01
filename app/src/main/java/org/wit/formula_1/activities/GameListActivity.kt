package org.wit.formula_1.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_game_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.formula_1.R
import org.wit.formula_1.main.MainApp
import org.wit.formula_1.models.GameModel

class GameListActivity : AppCompatActivity(), GameListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_list)
        app = application as MainApp
        toolbarMain.title = "Games List-"
        setSupportActionBar(toolbarMain)

        val layoutManager = LinearLayoutManager(this)
        recyclerViewGame.layoutManager = layoutManager
        recyclerViewGame.adapter = GameAdapter(app.games.findAll(), this)
        loadGames()
    }

    private fun loadGames() {
        showGames( app.games.findAll())
    }

    fun showGames (games: List<GameModel>) {
        recyclerViewGame.adapter = GameAdapter(games, this)
        recyclerViewGame.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_start, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<MainActivity>(0)

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onGameClick(game: GameModel) {
        startActivityForResult(intentFor<MainActivity>().putExtra("game_edit", game), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadGames()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
