package com.github.johnnysc.ecp.data.dictionary

import com.google.gson.annotations.SerializedName

interface DefinitionCloud {
    fun <T> map(mapper: DefinitionMapper<T>): T

    data class Base(
        @SerializedName("definition")
        private val definition: String,

        @SerializedName("example")
        private val example: String,

        @SerializedName("synonyms")
        private val _synonyms: List<String>?,
        private val synonyms: List<String> = _synonyms ?: listOfNotNull(),

        @SerializedName("antonyms")
        private val _antonyms: List<String>?,
        private val antonyms: List<String> = _antonyms ?: listOfNotNull()
    ) : DefinitionCloud {

        override fun <T> map(mapper: DefinitionMapper<T>) =
            mapper.map(definition, example, synonyms, antonyms)
    }

    interface DefinitionMapper<T> {
        fun map(
            definition: String,
            example: String,
            synonyms: List<String>?,
            antonyms: List<String>?
        ): T
    }
}


