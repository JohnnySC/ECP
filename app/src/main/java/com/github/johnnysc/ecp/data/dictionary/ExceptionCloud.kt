package com.github.johnnysc.ecp.data.dictionary

import com.google.gson.annotations.SerializedName

interface ExceptionCloud {
    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("title")
        private val tile: String,
        @SerializedName("message")
        private val message: String,
        @SerializedName("resolution")
        private val resolution: String,
    ) : ExceptionCloud {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(tile, message, resolution)
    }

    interface Mapper<T> {
        fun map(tile: String, message: String, resolution: String): T
    }
}