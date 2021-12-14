package com.github.johnnysc.ecp.data.dictionary

import com.google.gson.annotations.SerializedName

interface PhoneticCloud {
    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("text")
        private val text: String,

        @SerializedName("audio")
        private val audio: String
    ) : PhoneticCloud {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(text, audio)
    }

    interface Mapper<T> {
        fun map(text: String, audio: String): T
    }
}