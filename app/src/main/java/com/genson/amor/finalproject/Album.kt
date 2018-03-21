package com.genson.amor.finalproject

import com.google.gson.annotations.SerializedName

/**
 * Created by Genson on 21/03/2018.
 */
class Album (
        @SerializedName("#text")
        val text: String,
        val albumname: String,
        val bandName: String

)