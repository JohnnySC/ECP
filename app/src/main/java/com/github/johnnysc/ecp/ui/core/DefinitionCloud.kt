package com.github.johnnysc.ecp.ui.core

import com.google.gson.annotations.SerializedName

interface DefinitionCloud {

    data class Base(
        @SerializedName("definition")
        private val definition: String,

        @SerializedName("example")
        private val example: String,

        @SerializedName("synonyms")
        private val synonyms: List<String>?,

        @SerializedName("antonyms")
        private val antonyms: List<String>?
    )
}