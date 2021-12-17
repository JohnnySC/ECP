package com.github.johnnysc.ecp.data.dictionary

import com.google.gson.annotations.SerializedName

interface WordCloud {
    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("word")
        private val word: String,

        @SerializedName("phonetics")
        private val phonetics: List<PhoneticCloud>,

        @SerializedName("meanings")
        private val meanings: List<MeaningCloud>
    ) : WordCloud {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(word, phonetics, meanings)
    }

    interface Mapper<T> {
        fun map(word: String, phonetics: List<PhoneticCloud>, meanings: List<MeaningCloud>): T
    }
}