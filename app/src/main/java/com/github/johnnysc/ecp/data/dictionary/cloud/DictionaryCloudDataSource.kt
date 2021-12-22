package com.github.johnnysc.ecp.data.dictionary.cloud

import com.github.johnnysc.ecp.data.core.ServerExceptionData
import com.github.johnnysc.ecp.data.dictionary.WordCloud
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface DictionaryCloudDataSource<T> {
    suspend fun searchWord(language: String, word: String): List<WordCloud>

    class Base(
        private val dictionaryService: DictionaryService,
        private val gson: Gson,
        private val dictionaryTypeToken: DictionaryTypeToken,
    ) : DictionaryCloudDataSource<List<WordCloud>> {
        override suspend fun searchWord(language: String, word: String): List<WordCloud> = try {
            gson.fromJson(
                dictionaryService.word(language, word).string(),
                dictionaryTypeToken.type
            )
        } catch (e: ServerExceptionData) {
            throw ServerExceptionData(e.message.toString())
        }
    }
}

class DictionaryTypeToken : TypeToken<List<WordCloud>>()

