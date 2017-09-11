package com.example.user.lanrecord

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_add_game.*


/**
 * Created by User on 17.8.2017.
 */
class AddGame: Activity(), AdapterView.OnItemSelectedListener {

   private lateinit var map: String
    private var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_game)
        initializeSpinner()

        btn_add.setOnClickListener {
            val myScore = et_your_score.text
            val enemyScore = et_enemy_score.text

            if (myScore.isEmpty() || enemyScore.isEmpty()) {
                Toast.makeText(this,"Invalid score", Toast.LENGTH_SHORT)
            } else {
                val myS = myScore.toString()
                val enemyS = enemyScore.toString()
                val mySInt = Integer.parseInt(myS)
                val enemySInt = Integer.parseInt(enemyS)

                when {
                    mySInt > enemySInt -> MainActivity.win.add(1)
                    mySInt == enemySInt -> MainActivity.draw.add(1)
                    else -> MainActivity.loss.add(1)
                }

                when {
                    mySInt > enemySInt -> result = "W"
                    mySInt == enemySInt -> result = "D"
                    else -> result = "L"
                }

                val game = GameModel(map, myS, enemyS, result)
                MainActivity.games!!.add(game)
                finish()
            }
        }

        btn_cancel.setOnClickListener {
            finish()
        }
    }

    fun initializeSpinner() {
        val spinner = findViewById(R.id.spinner_maps) as Spinner
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.maparray, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(position) {
            0 -> map = "de_mirage"
            1 -> map = "de_cache"
            2 -> map = "de_inferno"
            3 -> map = "de_nuke"
            4 -> map = "de_overpass"
            5 -> map = "de_train"
            6 -> map = "de_dust2"
            7 -> map = "de_cbble"
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}