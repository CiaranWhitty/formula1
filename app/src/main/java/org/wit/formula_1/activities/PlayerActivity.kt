package org.wit.formula_1.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_player.*
import org.jetbrains.anko.AnkoLogger
import org.wit.formula_1.R


class PlayerActivity : AppCompatActivity(), AnkoLogger {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_player)

    toolbarAddPlayer.title = title
    setSupportActionBar(toolbarAddPlayer)

    btnAddPlayer.requestFocus()

    btnAddPlayer.setOnClickListener {


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
