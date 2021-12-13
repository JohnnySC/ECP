package com.github.johnnysc.ecp.data.dictionary

import com.google.gson.annotations.SerializedName

interface WordCloud {

    class Base(
        @SerializedName("word")
        private val word: String,

        @SerializedName("phonetics")
        private val phonetics: List<PhoneticCloud>,

        @SerializedName("meanings")
        private val meanings: List<MeaningCloud>
    ) : WordCloud
}