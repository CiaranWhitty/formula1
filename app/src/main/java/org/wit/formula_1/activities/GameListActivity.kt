package org.wit.formula_1.activities


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_game_list.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.card_game.view.*
import org.wit.formula_1.R
import org.wit.formula_1.main.MainApp
import org.wit.formula_1.models.GameModel

class GameListActivity : AppCompatActivity() {

lateinit var app: MainApp

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_game_list)

    app = application as MainApp

    toolbarMain.title = title
    setSupportActionBar(toolbarMain)


}
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


}

