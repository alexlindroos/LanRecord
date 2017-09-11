package com.example.user.lanrecord

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_game.*

class MainActivity : AppCompatActivity() {

    companion object {
        var games: ArrayList<GameModel>? = null
        var win = arrayListOf<Int>()
        var draw = arrayListOf<Int>()
        var loss = arrayListOf<Int>()
    }

    private var adapter: GameAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        setupList()

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            val intent = Intent(this, AddGame::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    fun initialize() {
        games = ArrayList()
        layoutManager = LinearLayoutManager(this)
        adapter = GameAdapter(games!!,applicationContext)
    }

    fun updateView() {
        adapter?.notifyDataSetChanged()
        val w = win.size.toString()
        val d = draw.size.toString()
        val l = loss.size.toString()

        txt_w.text = "$w W"
        txt_d.text = "$d D"
        txt_l.text = "$l L"

    }

    fun setupList() {
        rv_games.layoutManager = layoutManager
        rv_games.adapter = adapter
    }
}
