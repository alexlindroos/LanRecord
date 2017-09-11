package com.example.user.lanrecord

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


/**
 * Created by User on 17.8.2017.
 */
class GameAdapter(val games: ArrayList<GameModel>, val context: Context): RecyclerView.Adapter<GameAdapter.GameAdapterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameAdapterViewHolder(layoutInflater.inflate(R.layout.row_game, parent, false))
    }

    override fun onBindViewHolder(holder: GameAdapterViewHolder, position: Int) {

        val game = games[position]
        val myScore = game.myScore
        val enemyScore = game.enemyScore
        val map = game.map

        val img = context.resources.getIdentifier("com.example.user.lanrecord:drawable/" + map, null, null)

        holder.image.setImageResource(img)
        holder.map.text = game.map
        holder.score.text = "$myScore - $enemyScore"
        holder.result.text = game.result
    }

    override fun getItemCount(): Int = games.size

    class GameAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        val image = itemView.findViewById(R.id.img_map) as ImageView
        val map = itemView.findViewById(R.id.txt_map) as TextView
        val score = itemView.findViewById(R.id.txt_score) as TextView
        val result = itemView.findViewById(R.id.txt_result) as TextView
    }

}