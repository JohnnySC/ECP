package com.github.johnnysc.ecp.data.dictionary

interface DefinitionData {
    fun <T> mapper(mapper: Mapper<T>): T

    data class Base(
        private val definition: String,
        private val example: String,
        private val synonyms: List<String>,
        private val antonyms: List<String>
    ) : DefinitionData {
        override fun <T> mapper(mapper: Mapper<T>) =
            mapper.map(definition, example, synonyms, antonyms)
    }

    interface Mapper<T> {
        fun map(
            definition: String,
            example: String,
            synonyms: List<String>,
            antonyms: List<String>
        ): T
    }
}