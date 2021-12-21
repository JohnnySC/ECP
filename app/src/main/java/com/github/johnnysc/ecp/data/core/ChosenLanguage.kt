package com.github.johnnysc.ecp.data.core

import com.github.johnnysc.ecp.core.Save

interface ChosenLanguage : Read<String>, Save<String> {
    class Base(private val preferenceDataSource: PreferenceDataSource) : ChosenLanguage {

        override fun save(data: String) = preferenceDataSource.save(KEY, data)
        override fun read() = preferenceDataSource.read(KEY, "en")

        companion object {
            private const val KEY = "chosenLanguageKey"
        }
    }
}