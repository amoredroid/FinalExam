package com.genson.amor.finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val ALBUM_KEY_TITLE = "title"
    private val ALBUM_KEY_SPRITES = "sprites"
    private val ALBUM_KEY_BANDNAME = "band"
    private val searchAlbum = ArrayList<Album>()
    private val url = "http://ws.audioscrobbler.com/2.0/?method=artist.search&artist=cher&api_key=b9e769d658f3b88e3e0fc553a27ce5cd&format=json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        progressBar.visibility = View.VISIBLE
        searchAlbum()

    }

    private fun searchAlbum() {

        doAsync {
            var tempName = eT_albumSearch.text.toString()
            val resulJson = URL(url + tempName).readText()
            val jsonObject = JSONObject(resulJson)
            val albumName = jsonObject.getString(ALBUM_KEY_TITLE)
            val albumTitle = jsonObject.getString(ALBUM_KEY_BANDNAME)
            val albumSprite = jsonObject.getJSONObject(ALBUM_KEY_SPRITES)



            uiThread {

                recyclerView.adapter = AlbumAdapter(searchAlbum)
                searchAlbum.add(Album(Sprite(albumSprite),
                        albumName,
                        albumTitle

                ))

                tV_albumTitle.text = albumName.substring(0, 1).toUpperCase() + albumName.substring(1)
                tV_bandName.text = albumTitle.substring(0, 1).toUpperCase() + albumTitle.substring(1)


                Picasso.with(this@MainActivity).load(albumSprite).into(img_album)

                progressBar.visibility = View.GONE




            }

        }
    }

    private fun Sprite(albumSprite: JSONObject?): Any {}

}
