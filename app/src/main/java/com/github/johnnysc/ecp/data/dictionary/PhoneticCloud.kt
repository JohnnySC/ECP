package com.github.johnnysc.ecp.data.dictionary

import com.google.gson.annotations.SerializedName

interface PhoneticCloud {

    data class Base(
        @SerializedName("text")
        private val text: String,

        @SerializedName("audio")
        private val audio: String
    ) : PhoneticCloud
}