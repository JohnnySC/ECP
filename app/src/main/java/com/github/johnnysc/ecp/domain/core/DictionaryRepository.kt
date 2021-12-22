package com.github.johnnysc.ecp.domain.core

import com.github.johnnysc.ecp.data.dictionary.ChosenLanguage
import com.github.johnnysc.ecp.data.dictionary.WordData

interface DictionaryRepository {

    fun chosenLanguage(): ChosenLanguage
    fun changeLanguage(value: String)
    suspend fun searchWord(): List<WordData>
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
