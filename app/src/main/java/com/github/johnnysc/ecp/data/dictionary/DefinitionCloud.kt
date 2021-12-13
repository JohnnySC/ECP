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
        private val synonyms: List<String>?,

        @SerializedName("antonyms")
        private val antonyms: List<String>?
    ) : DefinitionCloud {

        override fun <T> map(mapper: DefinitionMapper<T>) =
            mapper.map(definition, example, synonyms, antonyms)
    }
}