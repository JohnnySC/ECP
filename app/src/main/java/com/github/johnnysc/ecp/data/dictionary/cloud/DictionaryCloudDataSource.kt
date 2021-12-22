package com.github.johnnysc.ecp.data.dictionary.cloud

import com.github.johnnysc.ecp.data.dictionary.WordCloud

interface DictionaryCloudDataSource {

    suspend fun searchWord(word: String): List<WordCloud>
}