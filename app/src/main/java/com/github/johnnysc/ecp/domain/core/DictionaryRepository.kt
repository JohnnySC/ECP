package com.github.johnnysc.ecp.domain.core

import com.github.johnnysc.ecp.data.dictionary.WordData

interface DictionaryRepository {

    fun chosenLanguage(): String
    fun changeLanguage(value: String)
    suspend fun searchWord(word: String): List<WordData>
}

enum class Language {
    en,
    es,
    fr,
    ja,
    ru,
    de,
    tr
}
