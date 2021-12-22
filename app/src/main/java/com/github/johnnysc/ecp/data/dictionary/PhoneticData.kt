package com.github.johnnysc.ecp.data.dictionary

interface PhoneticData {
    fun <T> mapper(mapper: Mapper<T>): T

    data class Base(
        private val text: String,
        private val audio: String
    ) : PhoneticData {
        override fun <T> mapper(mapper: Mapper<T>) = mapper.map(text, audio)
    }

    interface Mapper<T> {
        fun map(text: String, audio: String): T
    }
}