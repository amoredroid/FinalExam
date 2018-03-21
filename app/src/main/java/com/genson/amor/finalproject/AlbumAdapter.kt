package com.genson.amor.finalproject

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album.view.*
import android.content.Context

/**
 * Created by Genson on 21/03/2018.
 */
class AlbumAdapter(var context: Context,
        private val album: ArrayList<Album>) : RecyclerView.Adapter<AlbumAdapter.CustomViewHolder>() {


    override fun getItemCount(): Int {
        return album.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {

        holder?.itemView?.tV_albumTitle?.text = album[position].albumname
        holder?.itemView?.tV_bandName?.text = album[position].bandName

        val imgHolder = holder?.view?.img_album
        Picasso.with(holder?.view?.context).load(album[position].text).into(imgHolder)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {

        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.activity_album, parent, false)
        return CustomViewHolder(itemView)
    }


    class CustomViewHolder(var view: View) : RecyclerView.ViewHolder(view)


}



