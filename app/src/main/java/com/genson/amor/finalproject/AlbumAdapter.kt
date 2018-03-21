package com.genson.amor.finalproject

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Genson on 21/03/2018.
 */
class AlbumAdapter(private val album: ArrayList<Album>) : RecyclerView.Adapter<AlbumAdapter.CustomViewHolder>() {


    override fun getItemCount(): Int {
        return album.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {

        holder?.itemView?.tV_albumTitle?.text = album[position].albumname
        holder?.itemView?.tV_bandname?.text = album[position].bandName

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {

        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.activity_main, parent, false)
        return CustomViewHolder(itemView)
    }


    class CustomViewHolder(var view: View) : RecyclerView.ViewHolder(view)


}

