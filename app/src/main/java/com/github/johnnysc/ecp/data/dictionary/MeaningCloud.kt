package com.github.johnnysc.ecp.data.dictionary

import com.google.gson.annotations.SerializedName

interface MeaningCloud {
    data class Base(

        @SerializedName("partOfSpeech")
        private val partOfSpeech: String,

        @SerializedName("definitions")
        private val definitions: List<DefinitionCloud>

    ): MeaningCloud
}