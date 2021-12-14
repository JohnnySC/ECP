package com.github.johnnysc.ecp.data.dictionary

import com.google.gson.annotations.SerializedName

interface DefinitionCloud {
    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("definition")
        private val definition: String,

        @SerializedName("example")
        private val example: String,

        @SerializedName("synonyms")
        private val synonyms: List<String>?,

        @SerializedName("antonyms")
        private val antonyms: List<String>?,
    ) : DefinitionCloud {

        override fun <T> map(mapper: Mapper<T>) =
            mapper.map(
                definition,
                example,
                synonyms ?: emptyList(),
                antonyms ?: emptyList()
            )
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
