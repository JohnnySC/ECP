package com.github.johnnysc.ecp.data.dictionary.cloud

import com.github.johnnysc.ecp.data.dictionary.WordCloud

interface DictionaryCloudDataSource {

    suspend fun fetchWord(language: String, word: String): List<WordCloud>
}