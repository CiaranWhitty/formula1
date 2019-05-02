package org.wit.formula_1.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_player.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.formula_1.R
import org.wit.formula_1.helpers.readImage
import org.wit.formula_1.helpers.readImageFromPath
import org.wit.formula_1.helpers.showImagePicker
import org.wit.formula_1.main.MainApp
import org.wit.formula_1.models.PlayerModel

class PlayerActivity : AppCompatActivity(), AnkoLogger {

    var player = PlayerModel()
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1
    var editP = false

override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_player)
    app = application as MainApp

    toolbarAddPlayer.title = "Player-"
    setSupportActionBar(toolbarAddPlayer)

    btnAddPlayer.requestFocus()


    chooseImage.setOnClickListener {
        showImagePicker(this, IMAGE_REQUEST)
    }


    if (intent.hasExtra("player_edit")) {
        editP = true
        player = intent.extras.getParcelable<PlayerModel>("player_edit")
        playerName.setText(player.name)
        playerPoints.setText(player.points)
        playerImage.setImageBitmap(readImageFromPath(this, player.image))
        if (player.image != null) {
            chooseImage.setText(R.string.change_player_image)
        }
        btnAddPlayer.setText(R.string.save_player)
    }

    btnAddPlayer.setOnClickListener() {
        player.name = playerName.text.toString()
        player.points= playerPoints.text.toString()

        if (player.name.isEmpty()) {
            toast(R.string.enter_player_title)
        } else {
            if (editP) {
                app.players.update(player.copy())
            } else {
                app.players.create(player.copy())

            }
        }
        info("add Button Pressed: $playerName")
        setResult(AppCompatActivity.RESULT_OK)
        finish()
    }

}


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    player.image = data.getData().toString()
                    playerImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_player_image)
                }
            }
        }
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_player, menu)
        if (editP && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override  fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.item_delete_player -> {
                app.players.delete(player)
                finish()
            }

            R.id.item_cancel_player -> {
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

}
