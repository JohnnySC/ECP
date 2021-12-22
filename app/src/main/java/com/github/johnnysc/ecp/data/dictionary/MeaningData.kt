package com.github.johnnysc.ecp.data.dictionary

interface MeaningData {
    fun <T> mapper(mapper: Mapper<T>): T

    data class Base(
        private val partOfSpeech: String,
        private val definitions: List<DefinitionData>
    ) : MeaningData {
        override fun <T> mapper(mapper: Mapper<T>) = mapper.map(partOfSpeech, definitions)
    }

    interface Mapper<T> {
        fun map(partOfSpeech: String, definitions: List<DefinitionData>): T
    }
}