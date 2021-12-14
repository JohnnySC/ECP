package com.github.johnnysc.ecp.data.dictionary

import com.google.gson.annotations.SerializedName

interface MeaningCloud {
    fun <T> map(mapper: Mapper<T>): T

    data class Base(

        @SerializedName("partOfSpeech")
        private val partOfSpeech: String,

        @SerializedName("definitions")
        private val definitions: List<DefinitionCloud>

    ) : MeaningCloud {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(partOfSpeech, definitions)
    }

    interface Mapper<T> {
        fun map(partOfSpeech: String, definitions: List<DefinitionCloud>): T
    }
}