package com.genson.amor.finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val searchAlbum = ArrayList<Album>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSearch.setOnClickListener {
            searchAlbum.clear()
            progressBar.visibility = View.VISIBLE

            searchAlbum()

        }

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun searchAlbum() {
        var tempName = eT_albumSearch.text.toString()
        val url1 = "https://ws.audioscrobbler.com/2.0/?method=album.search&album=$tempName&api_key=b9e769d658f3b88e3e0fc553a27ce5cd&format=json"
        for (i in 0..49) {

            doAsync {
                val resulJson = URL(url1).readText()
                val jsonObject = JSONObject(resulJson)

                val albumName = jsonObject.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                        .getJSONObject(i).getString("name")

                val albumTitle = jsonObject.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                        .getJSONObject(i).getString("artist")
                var albumImage = ""

                albumImage = if(jsonObject.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                                .getJSONObject(i).getJSONArray("image").getJSONObject(2).getString("#text") == ""){
                    jsonObject.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                            .getJSONObject(i).getJSONArray("image").getJSONObject(2).getString("")
                }else{
                    jsonObject.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                            .getJSONObject(i).getJSONArray("image").getJSONObject(2).getString("#text")
                }



                uiThread {

                    recyclerView.adapter = AlbumAdapter(this@MainActivity, searchAlbum)
                    searchAlbum.add(Album(
                            albumName,
                            albumTitle,
                            albumImage

                    ))

                    progressBar.visibility = View.GONE

                }

            }

        }

    }

}




