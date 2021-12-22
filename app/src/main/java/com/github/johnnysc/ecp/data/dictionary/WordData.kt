package com.github.johnnysc.ecp.data.dictionary

interface WordData {
    fun <T> mapper(mapper: Mapper<T>): T

    data class Base(
        private val word: String,
        private val phonetics: List<PhoneticData>,
        private val meanings: List<MeaningData>,
    ) : WordData {
        override fun <T> mapper(mapper: Mapper<T>) = mapper.map(word, phonetics, meanings)
    }

    interface Mapper<T> {
        fun map(word: String, phonetics: List<PhoneticData>, meanings: List<MeaningData>): T
    }
}